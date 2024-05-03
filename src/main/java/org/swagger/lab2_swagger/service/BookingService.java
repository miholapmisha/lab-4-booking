package org.swagger.lab2_swagger.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.swagger.lab2_swagger.dto.BookingDto;
import org.swagger.lab2_swagger.exception.BookingException;
import org.swagger.lab2_swagger.exception.NotFoundException;
import org.swagger.lab2_swagger.model.Account;
import org.swagger.lab2_swagger.model.Booking;
import org.swagger.lab2_swagger.model.TourPackage;
import org.swagger.lab2_swagger.model.User;
import org.swagger.lab2_swagger.repository.AccountRepository;
import org.swagger.lab2_swagger.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final AccountRepository accountRepository;

    private final UserSecurityService userSecurityService;

    private final TourPackageService tourPackageService;

    public List<BookingDto> getAll() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDto).toList();
    }

    @Transactional
    public BookingDto save(BookingDto bookingDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userSecurityService.getUserByUsername(currentUsername);
        TourPackage tourPackage = tourPackageService.findById(bookingDto.getTourPackageId());

        if (tourPackage == null) {
            throw new NotFoundException("Tour package not found");
        }
        Account account = accountRepository.findByUser(user);

        if (account.getBalance() == null || account.getBalance() < tourPackage.getPrice()) {
            throw new BookingException("Not enough for booking tour package");
        }

        Booking booking = new Booking();
        booking.setDate(LocalDateTime.now());
        booking.setTourPackage(tourPackage);
        booking.setTourist(user);
        booking.setStatus(bookingDto.getStatus());

        account.setBalance(account.getBalance() - tourPackage.getPrice());
        bookingRepository.save(booking);
        return convertToDto(booking);
    }

    public BookingDto findById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tour package not found"));

        return convertToDto(booking);
    }

    @Transactional
    public void deleteById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userSecurityService.getUserByUsername(currentUsername);
        long currentUserId = user.getId();
        long ownerBookingUserId = booking.getId();

        if (currentUserId != ownerBookingUserId && user.getRole().equals("ROLE_TOURIST")) {
            throw new BookingException("Restricted to delete booking because user is not owner");
        }

        bookingRepository.deleteById(id);
    }

    @Transactional
    public BookingDto update(Long id, BookingDto booking) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userSecurityService.getUserByUsername(currentUsername);
        Booking bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        long currentUserId = user.getId();
        long ownerBookingUserId = bookingToUpdate.getId();
        if (currentUserId != ownerBookingUserId && user.getRole().equals("ROLE_TOURIST")) {
            throw new BookingException("Restricted to update booking because user is not owner");
        }

        bookingToUpdate.setTourPackage(tourPackageService.findById(booking.getTourPackageId()));
        bookingToUpdate.setDate(LocalDateTime.now());
        bookingToUpdate.setTourist(user);
        bookingToUpdate.setStatus(booking.getStatus());

        return convertToDto(bookingToUpdate);
    }

    private BookingDto convertToDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setTourPackageId(booking.getTourPackage().getId());
        bookingDto.setTouristsId(booking.getTourist().getId());
        return bookingDto;
    }

}

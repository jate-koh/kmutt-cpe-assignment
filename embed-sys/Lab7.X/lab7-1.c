/*
 * File:   main.c
 * Author: jatekoh
 *
 * Created on March 23, 2023, 8:03 PM
 */

#define F_CPU 8000000L

#include <avr/interrupt.h>
#include <avr/io.h>
#include <util/delay.h>

volatile uint16_t counter = 0;
volatile int trigger = 0;
volatile int lock_trigger = 0;

void init() {
    
    // Set PB0 as output, and clear PORTB
    DDRB |= (1 << DDB0);
    PORTB = 0x0;

    
    // Set PD5 as output, and clear PORTD
    DDRD |= (1 << DDD5);
    PORTD = 0x0;
    
    // Setup PD2 as External Interrupt (INT0)
    DDRD |= (1 << DDD2);
    PORTD |= (1 << PORTD2);
    EIMSK |= (1 << INT0); // Set Interrupt Pin
    EICRA |= (1 << ISC01); // Falling Edge of INT0 generate request
    
    // Set Pre-scaler to 256
    TCCR1B |= (1 << CS12);
    
    // Setup Timer 1 to operate in normal mode
    TCCR1A = 0x0;
    
    // Set Initial Value for Timer 1 to 0
    TCNT1 = 0x0;
    
    // Enable Timer 1 Overflow Interrupts
    TIMSK1 |= (1 << TOIE1);
    
    sei(); // Global Interrupts
}

// Interrupt Service Routine for
// Overflow Interrupts Vector (p 66)
ISR(TIMER1_OVF_vect) {
    // Increment upon Interrupts
    counter++;

    if(lock_trigger == 0) {
        if(trigger == 1) PORTD |= (1 << PORTD5);
        else PORTD &= ~(1 << PORTD5);
    }
    
    // Toggle Trigger
    trigger ^= 1;
    
    // Clear Timer Output
    TCNT1 = 0x0;
}

// Interrupt Service Routine for
// Input Interrupt (p 66))
ISR(INT0_vect) {
//    // Clear PORTD
//    PORTD = 0x0;
//    
    // Clear Timer Output when unlocking
    if(lock_trigger == 1) TCNT1 = 0x0;
    
    // Toggle Lock Trigger
    lock_trigger ^= 1;
    
//    // Reset Trigger to 0
//    trigger = 0;
}

void loop() {
    while(1) {
        // Trigger RED LED based on lock state
        if(lock_trigger == 0) PORTB &= ~(1 << PORTB0);
        if(lock_trigger == 1) PORTB |= (1 << PORTB0);
    }
}

int main(void) {
    init();
    loop();
}

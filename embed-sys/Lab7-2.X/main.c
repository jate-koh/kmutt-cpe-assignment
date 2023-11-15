#define F_CPU 8000000L
#define LED1 PORTD5
#define LED2 PORTD6
#define LED3 PORTD7

#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
 

// direction: 0 is -> and 1 is <-
volatile int direction = 0;
volatile int counter = 0;
volatile int lock = 0;
volatile int reset = 0;
volatile int holdCounter = 0;

volatile uint16_t prevOutput;

void init() {
    
    // Set PD5, PD6, PD7 as output, and clear PORTD
    DDRD |= (1 << DDD5) | (1 << DDD6) | (1 << DDD7);
    PORTD = 0x0;
    
    // Set PB0 as output and clear PORTB
    DDRB |= (1 << DDB0);
    PORTB = 0x0;
    
    // Configure Timer 1 in normal mode
    TCCR1A = 0x0;
    
    // Set Pre-scaler to 256
    TCCR1B = (1<< CS12); 
    
    // Enable Timer 1 Overflow Interrupts
    TIMSK1 |= (1 << TOIE1);
    
    // Set Initial Value for Timer 1 to 0
    TCNT1 = 0;
    
    // Setup PD2 as External Interrupt (INT0)
    DDRD |= (1 << DDD2);
    PORTD |= (1 << PORTD2);
    EIMSK |= (1 << INT0); // Set Interrupt Pin
    EICRA |= (1 << ISC01); // Falling Edge of INT0 generate request

    sei(); // Global Interrupts
}

void clear() {
    // Clear PORTD
    PORTD = 0x0;
}

void blinkLED(int count) {
    // Clear PORTD Every Time Before Blinking New One
    clear();
    
    if(count == 0) PORTD |= (1 << LED1);
    else if(count == 1) PORTD |= (1 << LED2);
    else if(count == 2) PORTD |= (1 << LED3);
    else clear();
}

// Interrupt Service Routine for
// Timer Overflow Interrupts
ISR(TIMER1_OVF_vect) {
    
    // Blink LED according to Counter
    blinkLED(counter);
    
    // Increment or Decrement Counter based on direction state
    if(direction == 0) counter++;
    else counter --;
    
    // Switch Direction Once Counter Reach Start/End
    if(counter == 2 || counter == 0) direction ^= 1;
}

// Interrupt Service Routine for
// External Button Interrupts
ISR(INT0_vect) {

    
    // If not in reset mode
    if(holdCounter < 2) {
        
        // Toggle Lock
        lock ^= 1;

        // Output Red LED based on Lock State
        if(lock == 0) PORTB &= ~(1 << PORTB0);
        if(lock == 1) PORTB |= (1 << PORTB0);

        // Save prevClock Output
        prevOutput = TCNT1;
    } 
    // If in reset mode
    else {
        
        // Blink Red LED 2 times as signal
        PORTB &= ~(1 << PORTB0);
        _delay_ms(100);
        PORTB |= (1 << PORTB0); 
        _delay_ms(100);
        PORTB &= ~(1 << PORTB0);
        _delay_ms(100);
        PORTB |= (1 << PORTB0);
        _delay_ms(100);
        PORTB &= ~(1 << PORTB0);
        
        // Reset Lock
        lock = 0;
        
        // Clear LED
        clear();
        
        // Reset Timer 1 Output
        TCNT1 = 0x0;
    }
    
    // Reset Hold Counter
    holdCounter = 0;

    
}

void checkHold() {
    
    // Button is pressed
    if(PIND & (1 << PIND2)) {
        
        // While holding a button
        while(PIND & (1 << PIND2)) {
            // Hold Counter increment every seconds
            holdCounter++;
            _delay_ms(1000);
        }
    }

}

void loop() {
    while(1) {
        
        checkHold();
        
        // In a Lock, Freeze Counter
        while(lock == 1) { 
            TCNT1 = prevOutput;
        }
    }
}

int main() {
    init();
    loop();
}

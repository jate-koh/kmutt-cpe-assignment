#define F_CPU 8000000L

#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>

int period;
int state;
volatile int check = 0;
volatile int check1 = 0;

void not_blink() {
    PORTD &= ~(1 << PORTD4);
  	PORTD &= ~(1 << PORTD5);
  	PORTD &= ~(1 << PORTD6);
}

void blink1() {
  	while (check == 1||check1 == 1);
  	PORTD |= (1 << PORTD4);
    state = 1;
    blink2();
}

void blink2() {
  	while (check == 1||check1 == 1);
  	_delay_ms(500);
    PORTD &= ~(1 << PORTD4); 
   	PORTD |= (1 << PORTD5);
    state = 2;
    blink3();
}

void blink3() {
  	while (check == 1||check1 == 1);
  	_delay_ms(500);
    PORTD &= ~(1 << PORTD5); 
   	PORTD |= (1 << PORTD6);
    state = 3;
    blink4();
}

void blink4() {
  	while (check == 1||check1 == 1);
  	_delay_ms(500);
    PORTD &= ~(1 << PORTD6); 
   	PORTD |= (1 << PORTD5);
    state = 4;
    blink5();
}

void blink5() {
  	while (check == 1||check1 == 1);
  	_delay_ms(500);
    PORTD &= ~(1 << PORTD5);
    PORTD |= (1 << PORTD4);
    state = 5;
    blink1();
}


int main (void)
{
  DDRD = 0b01110000;
  PORTD |= (1 << PORT2);
  
  TCCR1A = 0b00000000;
  TCCR1B = (1<< CS12) | (1 << CS10);
  
  while(1)
   {
    EICRA |= (1 << ISC00);
    EIMSK |= (1 << INT0);
    sei();
    
    blink1();
   }
}

ISR (INT0_vect)
{
  if (PIND & (1 << PORTD2)) {
    period = TCNT1 * 0.0000625 * 1024;
    if (period > 30) {
      check ^= 1 ;
    }
    if (period > 3000) {
      check1 ^= 1 ;
      not_blink();
    } 
  }
  else {
    TCNT1 = 0;
  }
}

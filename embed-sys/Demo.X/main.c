#define F_CPU 8000000L

#include <avr/interrupt.h>
#include <avr/io.h>
#include <util/delay.h>

volatile int trigger = 0;

void setup()
{
    DDRB |= (1 << DDB1);
    DDRD |= (1 << DDD2);
    
    PORTD |= (1 << PORTD2);
    sei();
    EIMSK |= (1 << INT0);
    EICRA |= (1 << ISC01);
}

void led()
{
    while (1)
    {
        while (trigger == 1);
        PORTB |= (1 << PORTB1);
        _delay_ms(2000);
        PORTB &= ~(1 << PORTB1);
        _delay_ms(2000);
    }
}

int debounceButton()
{
    _delay_ms(30);
    if (!(PIND & (1 << PIND2)))
    {
        _delay_ms(30);
        if (!(PIND & (1 << PIND2)))
        {
            return 1;
        }
    }
    return 0;
}

ISR (INT0_vect)
{
    if (debounceButton())
    {
        trigger ^= 1;
        PORTB &= ~(1 << PORTB1);
    }
}

int main(void) 
{
    setup();
    led();
}
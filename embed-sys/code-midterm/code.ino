// RED PB0 Output
// GREEN PB1 Output
// BLUE PB2 Output
#define RED PORTB0
#define GREEN PORTB1
#define BLUE PORTB2

// Photoresistor PC0 Input Analog

// Button PD4 Input

// ATTiny 85 Code
uint16_t adcValue;
uint16_t voltage;

void initIO() {
  // Set PB0, PB1, PB2 as Outputs
  DDRB |= (1 << DDB0) | (1 << DDB1) | (1 << DDB2);
  
  // // Set PC0 as Input and enable pull-up
  // DDRD &= ~(1 << DDD2);
  // PORTD |= (1 << PORTD2);
  
  // Set PD4 as Input and enable pull-up
  DDRD &= ~(1 << DDD4);
  PORTD |= (1 << PORTD4);
}

void initAdc() {
  	// Enable ADC and Set Prescale to 128
    ADCSRA |=(1 << ADEN) | (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0);
    
  	// Set Reference Voltage to AVcc
  	ADMUX |= (1 << REFS0); 
  	
  	// Set Channel to ADC1, MUX = 0001
  	ADMUX |= (1 << MUX0);
}

void setup() {
  initIO();
  initAdc();
}

// RED PB0 Output
// GREEN PB1 Output
// BLUE PB2 Output

void loop() {
  
  PORTB = 0x0;
  
  while( (PIND & (1 << PIND4))); //Wait for button to be pressed
  _delay_ms(30);
  while( !(PIND & (1 << PIND4) ) ); //Wait for button to be released
  _delay_ms(30);
  
  while( (PIND & (1 << PIND4))) { //Wait for button to be pressed
  _delay_ms(30);
  // Read ADC
  ADCSRA |= (1 << ADSC);
  while (ADCSRA & (1 << ADSC));
  adcValue = ADC;
  
  // Convert ADC to Voltage
  voltage = (adcValue * 5000) / 1024;
  uint16_t resistance = ( (voltage/1000) * 10000 )/( 5 - (voltage/1000) ); 
  uint16_t lux = resistance * (0.0691445);
  
	if(voltage < 2400) {
     	// White
     	PORTB |= (1 << RED);
      	PORTB |= (1 << GREEN);
      	PORTB |= (1 << BLUE);
    }
    else if(voltage < 2600 & voltage > 2400) {
  		// Red
    	PORTB |= (1 << RED);
      	PORTB &= ~(1 << GREEN);
      	PORTB |= (1 << BLUE);
    }
    else if(voltage < 2800 & voltage > 2600) {
  		// Magenta
    	PORTB &= ~(1 << RED);
      	PORTB |= (1 << GREEN);
      	PORTB &= ~(1 << BLUE);
    }
    else if(voltage < 3000 && voltage > 2800) {
  		// Yellow
    	PORTB |= (1 << RED);
      	PORTB |= (1 << GREEN);
      	PORTB &= ~(1 << BLUE);
    }
    else if(voltage < 3300 && voltage > 3000) {
  		// Sky
    	PORTB &= (1 << RED);
      	PORTB |= (1 << GREEN);
      	PORTB |= (1 << BLUE);
    }
    else if(voltage < 3500 && voltage > 3300) {
  		// Green
    	PORTB &= ~(1 << RED);
      	PORTB |= (1 << GREEN);
      	PORTB &= ~(1 << BLUE);
    }
    else if(voltage > 3500) {
  		//  Blue
    	PORTB &= ~(1 << RED);
      	PORTB &= ~(1 << GREEN);
      	PORTB |= (1 << BLUE);
    }
    else PORTB = 0x0;
    
      
  // Delay 1 second
  _delay_ms(300);
    
  }
  while( !(PIND & (1 << PIND4)) ); //Wait for button to be released
  _delay_ms(30);
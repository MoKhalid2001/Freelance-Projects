/*
 * ADC.c
 *
 *  Created on: Jul 4, 2024
 *      Author: mk225
 */

#include <avr/io.h>

#include "LIB/BIT_MATH.h"
#include "LIB/STD_TYPES.h"

void ADC_INIT(void){
	/*Select the voltage reference*/
	SET_BIT(ADMUX,REFS0);
	CLR_BIT(ADMUX,REFS1);

	/*Set Prescaler Value*/
	SET_BIT(ADCSRA,ADPS0);
	SET_BIT(ADCSRA,ADPS1);
	CLR_BIT(ADCSRA,ADPS2);

	/*Enable ADC Peripheral*/
	SET_BIT(ADCSRA,ADEN);
}

u16 ADC_READ(u8 channel_num){
	/*Set required channel*/
	ADMUX = (ADMUX & 0xF8) | (channel_num & 0x07);

	/*Start Conversion*/
	SET_BIT(ADCSRA,ADSC);

	/*Waiting until the conversion is complete*/
	while(GET_BIT(ADCSRA,ADIF) == 0);

	/*Clear the interrupt flag*/
	SET_BIT(ADCSRA,ADIF);

	/*Return Conversion Result*/
	return ADCL | (ADCH << 8);
}

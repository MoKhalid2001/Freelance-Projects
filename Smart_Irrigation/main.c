/*
 * main.c
 *
 *  Created on: Jul 4, 2024
 *      Author: mk225
 */

#include <avr/delay.h>
#include <avr/interrupt.h>

#include "LIB/BIT_MATH.h"
#include "LIB/STD_TYPES.h"
#include "LCD.h"

#define SOIL_CHANNEL 0
#define TEMP_CHANNEL 1

/* Variables */
u16 soil_read;
u16 celsius;
u8 rain;

void water_pump(void);
void soil_LEDs(void);

void main(void){
	/* Initiating */
	DDRA = 0; DDRD = 1;
	ADC_INIT();
	LCD_INIT();




	/* Welcome Screen */
	LCD_SET_CURSOR_POSITION(1,4);
	LCD_WRITE_STRING("Welcome to");
	LCD_SET_CURSOR_POSITION(2,1);
	LCD_WRITE_STRING("Smart Irrigation");
	_delay_ms(1000);
	LCD_WRITE_CMD(CLEAR_DISPLAY);

	while(1){

		/* Soil Sensor */
		LCD_WRITE_STRING("Soil read:");
		LCD_SET_CURSOR_POSITION(2,1);
		soil_read = 100 - (ADC_READ(SOIL_CHANNEL) * 100) / 1023;
		LCD_WRITENUMBER(soil_read);
		_delay_ms(1000);
		LCD_WRITE_CMD(CLEAR_DISPLAY);

		/* Temperature  Sensor */
		LCD_WRITE_STRING("Temperature read:");
		LCD_SET_CURSOR_POSITION(2,1);
		celsius = (ADC_READ(TEMP_CHANNEL) * 4.88);
		celsius = (celsius / 10.00);
		LCD_WRITENUMBER(celsius);
		_delay_ms(1000);
		LCD_WRITE_CMD(CLEAR_DISPLAY);

		/* Rain  Sensor */
		LCD_WRITE_STRING("Rain status:");
		LCD_SET_CURSOR_POSITION(2,1);
		rain = GET_BIT(PINA,PIN2);
		if (rain) LCD_WRITE_STRING("ON");
		else LCD_WRITE_STRING("OFF");
		_delay_ms(1000);
		LCD_WRITE_CMD(CLEAR_DISPLAY);

		soil_LEDs();
	}
}

void soil_LEDs(void){
	if (soil_read <= 25){
		SET_BIT(PORTD,PIN1);
		CLR_BIT(PORTD,PIN2);
		CLR_BIT(PORTD,PIN3);
	}
	else if (soil_read > 25 && soil_read <= 50){
		CLR_BIT(PORTD,PIN1);
		SET_BIT(PORTD,PIN2);
		CLR_BIT(PORTD,PIN3);
	}
	else if (soil_read > 50){
		CLR_BIT(PORTD,PIN1);
		CLR_BIT(PORTD,PIN2);
		SET_BIT(PORTD,PIN3);
	}
}

void water_pump(void){
	if (rain == 0){
		if (soil_read < 50) SET_BIT(PORTD,PIN0);
		else CLR_BIT(PORTD,PIN0);
	}
	else CLR_BIT(PORTD,PIN0);
}

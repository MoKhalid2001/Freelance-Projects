
# Automated Irrigation System using ATmega32 Microcontroller


## Overview
This project is an automated irrigation system designed to monitor and control soil moisture levels using various sensors and ATmega32 microcontroller.
## Components
- **Soil Moisture Sensor**
  - Measures the moisture content in the soil.
  - Provides analog output based on soil moisture levels.

- **Rain Sensor (HL-83)**
  - Detects the presence of rain.
  - Prevents the irrigation system from activating during rainfall to conserve water.

- **Temperature Sensor**
  - Measures the ambient temperature.
  - Provides temperature data which can be used to optimize irrigation based on environmental conditions.

- **LCD Screen**
  - Displays real-time data from the sensors, including soil moisture levels, temperature, and rain status.
  - Helps in monitoring and adjusting the system parameters.

- **Water Pump**
  - Controlled by the microcontroller to irrigate the soil based on the moisture sensor readings.
  - Activates only when the soil moisture level is below a certain threshold and rain is not detected.

- **Soil Indicator**
  - Provides visual indications of soil moisture status using LEDs.
  - Helps in easily identifying the current moisture level of the soil.

- **Microcontroller**
  - Central unit that processes the data from sensors.
  - Controls the water pump and displays data on the LCD screen.
  - Ensures the system operates autonomously by making decisions based on sensor inputs.

## Working
1. **Soil Moisture Monitoring**
   - The soil moisture sensor continuously monitors the soil moisture level.
   - The analog or digital output from the sensor is read by the microcontroller.

2. **Rain Detection**
   - The rain sensor checks for the presence of rain.
   - If rain is detected, the microcontroller disables the water pump to prevent unnecessary watering.

3. **Temperature Monitoring**
   - The temperature sensor provides the ambient temperature data.
   - This data can be used to make more informed irrigation decisions.

4. **Water Pump Control**
   - Based on the soil moisture readings and rain detection, the microcontroller decides whether to activate the water pump.
   - The water pump irrigates the soil only when necessary, ensuring efficient water usage.

5. **Display and Indicators**
   - The LCD screen displays real-time sensor data, including soil moisture, temperature, and rain status.
   - LEDs indicate the current soil moisture status, providing a quick visual reference.

## Circuit Diagram

![Circuit Diagram](https://i.imgur.com/Zap7XA2.png)


# Overview

Many public wi-fi hotspots require that you first open a webpage to agree to their terms and conditions. This program will accept those terms and conditions for you so you don't have to open a browser window and accept them yourself.

Note:  This program makes certain assumptions about the wi-fi hotspot and is not guaranteed to work in all locations.

Also note: By running this program, you are still responsible for adhering to the network owner's terms and conditions. This program does not in any way absolve you from having to follow the terms and conditions. 

Run it from the command line:

    java -jar wifi.jar --verbose

# Command line arguments

    --verbose, -v
       Displays progress messages as the program runs.
    --help, -h
       Displays this help text.

# Opening in Eclipse

To generate the necessary files needed to open the project in Eclipse, navigate to the project root and run the following Maven command:

    mvn eclipse:eclipse

# How to build

To build the project, navigate to the project root and run the following Maven command:

    mvn clean compile assembly:single
    
This will generate a runnable JAR file that contains all dependencies.
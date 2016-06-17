# ICHACK16-ProcrastEnabler
Eye-blinking pattern recognition that runs shell scripts
This project was build for the ICHACK16 hackathon.



#What is ProcrastEnabler
A Java desktop application where you can add eye-blinking patterns and shell scripts associated with them.
Eye blinks are recorded through your webcam.
It is build using OpenCV for image processing and Swing for the GUI.

#Dependencies
 - OpenCV 3.1.0

#Challenges we ran into
Finding OpenCV documentation for Java, Issues with OpenCV on MacOS

#Accomplishments we are proud of
Eye tracking and managing to handle the API

#Crediting
We used a haarcascade xml for eye detection from:
https://github.com/Itseez/opencv/tree/master/data/haarcascades

#What's next
 - Extension to generate shell scripts through buttons of commonly used operations(e.g. open a certain website on a new window)
 - Camera blink can be fixed and closed eye calibration should be made easier to use(may need 3-4 attempts to detect closed eyes - it informs user though)

#Disclaimers
 - Needs a light-rich room to work properly
 - If you are a windows user the bash scripts won't work, but you can add your own

#Contributors
 - Han Thi Nguyen
 - Panayiotis Panayiotou
 - Rishabh Jain
 - Mihai Vanea


Breakout!
=========
- CS349 Assignment 1
- Created by Dongwoo Son
- Created at Jan 28, 2015
- Email: d3son@uwaterloo.ca

Preview
-------
![alt text](http://dongwoo1005.github.io/image/Breakout.png "Breakout level 3 view")


How to make and run
-------------------
1. Go to src folder
2. Use Makefile
    - Run "make" to compile the .java files
    - Run "make run" to compile and run with splash screen
    - Run "make clean" to clean .class files

Command line argument
---------------------
### (1) FPS:
- must be first argument
- must be an integer
- recommend FPS in 25-40
- default is 40

### (2) Ball Speed:
- must be second argument
- must be an integer
- recommend the ball speed to be under 300
- default is 125

### (3) Splash Screen
- must pass this VM option  below to open with the splash screen
- -splash:../images/BreakoutSplash.png

### Example:
java -splash:../images/BreakoutSplash.png Breakout 40 125 will open up with the splash screen with the FPS set to 40 and the Ball Speed set to 125 in the default window screen

How to play the game
--------------------
1. Move your mouse to control the paddle
2. Press the key Space to Start Play / Pause / Resueme
3. Press the key Q to Quit the game

### CHEAT FUNCTIONS
1. Press the key C to Clear the current game level
2. Press the key R to force Restart the game

Additional features
-------------------
1. Level implementation
    - Level 1: 1 row, ball speed at default window size is 125 (Easy - Testing Mode)
    - Level 2: 5 rows, ball speed at default window size is 175 (Normal)
    - Level 3: 8 rows, ball speed at default window size is 225 (Hard)

2. Lives
    - You have three chance to play the game

3. Highest Score
    - Highest score is displayed on the top right corner

Scores
------
+ 1 for every bounce off the wall or paddle
+ 110 for destroying red block
+ 90 for destroying orange block
+ 70 for destroying yellow block
+ 50 for destroying green block
+ 30 for destorying blue block
+ 10 for destroying violet block

### Clear Bonus:
+ 1000 x current level
+ 500 x number of lives

================================================

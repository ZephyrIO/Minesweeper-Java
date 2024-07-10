#! /usr/bin/bash

javac -d bin src/cs1302/game/MinesweeperGame.java
javac -d bin -cp bin src/cs1302/game/MinesweeperDriver.java
javadoc -d doc -sourcepath src -subpackages cs1302

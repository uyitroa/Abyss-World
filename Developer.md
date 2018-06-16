# Design

## Introduction

S1 - Loading - (P1)

// Story

S2 - Show the background with the factory who is polluting the world - OK

S4 - Show the background of revolution -

S3 - Show background dead and the story of the player -> OK

S5 - The player decided to stop the factory by himself -> OK

---> Go to the first level

## In the game view

+ Time
+ Score

## Level 1

The player wears the uniform of IBM worker to work in the garbage. The job is to classify all the garbage in to the right type.

The mission is to finish the job in certain time (20s) with least mistake ( score >= 100 points)

- One correct type: 10 points
- One incorrect type: -3 points


_Design_

+ Player with worker uniform: uniform must have the IBM logo (text). The actions of player (for designing sprite): go, turn, collect the garbage, throw the garbage to the trash bin
+ Trash bin -> 3 types with the logo of tri-color... search on the internet. The sprite: open and swallow the garbage

+ Garbage types -> 3 type for each (9 types) -> with the animation of the garbage fly into the trash bin
+ The next level door -> a simple door
+ The garbage output pipe -> a simple pile
+ The background (map) -> show the working place + the factory

_Classes_

- Level1Screen
+ player
+ listOfTrashBin
+ listOfGarbage
+ startingTime
+ MAX_PLAYING_TIME
+ MIN_SCORE
+ xMin
+ yMin
+ xMax
+ yMax

- GameEntity: Abstract class
+ x
+ y
+ getX()
+ getY()
+ setX()
+ setY()

- Player
+ name
+ score
+ holdGarbage
+ moveUP()
+ moveRight()
+ moveDown()
+ moveLeft()
+ pickAGarbage(Garbage gb)
+ throwAgarbageToATrashBin(Garbage gb, Trashbin trashbin)
+ getScore()
+ getName()
+ setName()
+ init()
+ updateScore(int addedPoint)
+ sprite

- TrashBin
+ type: enum: TB_PLASTIC, TB_ORGANIC, TB_PAPER
+ imageSrc
+ render(x,y)
+ getType()

- Garbage
+ type: enum: G_PLASTIC_1, G_PLASTIC_2, G_PLASTIC_3,G_ORGANIC_1, G_ORGANIC_2, G_ORGANIC_3, G_PAPER_1, G_PAPER_2, G_PAPER_3,
+ imageSrc
+ render(x,y)
+ getTrashBinType()

- GarbagePipe
+ GARBAGE_GENERATED_TIME
+ lastGarbageTime
+ generateAGarbage()

## Level 3 -> To be decided

Mission: To find the admin password to access to the admin system and destroy the core processor

- Solution 1: The system ask the player a question (this question must be very important, make the player think about the future, about the pollution, and about that he should destroy the factory or not ....).

```
Question: What is more important?
1 - Protect environment
2 - Develop technology to make human like better
```

-> Correct answer: Protect Environment - that is the purpose of the founder of the factory.

-> Need to design the background somehow to make people think that the correct answer is `2`

'I see, you've decided to disable this factory.
But have you really give a thought?
This factory is humanity's hope.
Don't let your feeling control you.
Life of one person or the future of humanity?
Which one?'
Just before to click the button, re-ask.

_Design_
The player with the system screen.
The questions and the button to choose the answer A / B

## End game

- The factory has been stop, the sun shines after very long time...
- The phone rang, the player picks up the phone
- Everything is black -> after 2s, the text ..."Your mom is dead..."


_Design_

- The factory stops and the sun shines to the city (with some people see the sun after very long time)
- The player receives a phone call
- Last screen: "Your mom is dead" -> Simple black screen and text
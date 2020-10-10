# LPOO T7G10 - Moon Buggy II : When Gravity Fails!

This project is based on the Linux Terminal game "Moon Buggy". In this game, you drive a vehicle across the moon's surface and try to avoid the dangerous craters by jumping over them. Along the way, there are also menacing aliens and robots that try to stop you, so you must shoot them down! The goal is to keep the buggy rolling as long as possible!

![full_video](https://user-images.githubusercontent.com/43822403/83338302-0aae4a80-a2bb-11ea-8578-b5a59f647388.gif)


This project was developed for LPOO class 2019/2020 by: 

- Caio Nogueira - up201806218@fe.up.pt

- Diogo Almeida - up201806630@fe.up.pt


## Implemented Features

The game contains the following functionalities:

- **Main Menu -** Where the player can start/exit the game, choose a buggy or access the scores list.
![main_menu](https://user-images.githubusercontent.com/43822403/83277056-6c8e8780-a1c9-11ea-8bc8-0a224348d12f.png)

- **Buggy Selection -** A place where the player can choose from a list of various buggies, each with different laser colors and animations.
![buggyselection_menu](https://user-images.githubusercontent.com/43822403/83276234-369cd380-a1c8-11ea-9726-fac29173908e.png)

- **Scores List -** A table containing the all-time best scores, which automatically updates.
![scores_menu](https://user-images.githubusercontent.com/43822403/83276230-36043d00-a1c8-11ea-9f6e-d7abe7a82ebb.png)
- **Pause Menu-** During the game, the player can pause the game by pressing either the 'P' or ESC keys.
![game4](https://user-images.githubusercontent.com/43822403/83296557-e4b87580-a1e8-11ea-913b-bc0b112eacab.png)
- **Jumping -** The buggy can jump over craters or alien attacks by pressing the space bar key.
![game2](https://user-images.githubusercontent.com/43822403/83276210-313f8900-a1c8-11ea-9652-4a3ff9b781c4.png)

- **Shooting -** The buggy can shoot lasers (with limited ammo) to take down aliens or robots by pressing the arrow keys.
![game1](https://user-images.githubusercontent.com/43822403/83276206-30a6f280-a1c8-11ea-8be0-2d67f0b7b351.png)

- **Crater Generation -** The surface contains craters that are randomly generated, the program also takes into account the minimum amount of floor required for the game to be possible.

- **Enemies -** During the game, aliens or robots are randomly generated, they each have their own stats and special attacks, so the player must shoot them down wisely.

- **Collision Detection -** The game detects when the buggy collides with the craters (with either its front or rear wheel) or gets hit by enemy attacks.
![game3](https://user-images.githubusercontent.com/43822403/83276215-3270b600-a1c8-11ea-9811-65d27be8965c.png)

- **Game Animations -** There is a different set of animations for each of the buggies. These effects, along with some star animations, help simulate movement even though the buggy is always in the same horizontal position.
![buggy1](https://user-images.githubusercontent.com/43822403/83276022-e7ef3980-a1c7-11ea-82df-4a285b9e3296.gif)
![buggy2](https://user-images.githubusercontent.com/43822403/83276051-f63d5580-a1c7-11ea-95a5-39fd48e7b3ca.gif)
![buggy3](https://user-images.githubusercontent.com/43822403/83276069-fccbcd00-a1c7-11ea-81c6-cf420bfc4065.gif)

- **Sound Effects (new)** - The game features sound effects for each buggy action during the game and has a different arcade music for each menu.


## Planned Features

- **Power-Ups and Lives System (removed) -** During the development of the game we realized power-ups didn't really make much sense in the context of the game, since the only movement the buggy does is jumping it would make these power-ups very easy to pick up.


## Architectural pattern

For the architectural pattern, we used the Model-View-Controller (MVC) pattern, which is commonly used for developing user interfaces, therefore perfectly suiting the game's development.

![mvc](https://user-images.githubusercontent.com/43822403/83278554-b1b3b900-a1cb-11ea-8037-7e4af20eff6e.png)


## Design

### Different random enemies should be created during the game - *Factory Method Pattern*

#### Problem in Context
Considering that there are two different types of enemies that can be randomly generated (Robots or Aliens, both subclasses of Enemy),  it is problematic to have a creation module of the code in the Surface class that both creates and decides which type of enemies are created, as this violates the **Single Responsibility Principle** and makes the enemy creation section much more complex. 

#### The Pattern
To solve this problem we decided to implement the **Factory Method Pattern**. This pattern allows you to pass the control of object creation to the subclasses of the Creator class, thus this makes enemy generation more controlled.

#### Implementation
The following diagram shows how the pattern’s roles were mapped to the application classes. 

![FactoryMethod](https://user-images.githubusercontent.com/43822403/83281064-71563a00-a1cf-11ea-8b8e-bb995d3e54a2.png)

These classes can be found in the following files:

- [Surface](../src/main/java/controller/Surface.java)

- [EnemyCreator](../src/main/java/controller/factory/EnemyCreator.java)

- [RandomEnemyCreator](../src/main/java/controller/factory/RandomEnemyCreator.java)

- [Enemy](../src/main/java/controller/Enemy.java)

- [Alien](../src/main/java/controller/Alien.java)

- [Robot](../src/main/java/controller/Robot.java)

#### Consequences
The use of the Factory-Method Pattern in the current design brings the following benefits:

- It avoids tight coupling between the enemies' creator and concrete enemies.

- By moving the enemy creation code into one place in the program, the design now respects the **Single Responsibility Principle**.

- It follows the **Open/Closed Principle**, meaning that it is possible to introduce new types of enemies and new ways of generating them into the game without breaking existing code.

### The game should behave differently depending on its state - *State Pattern*

#### Problem in Context
At any given moment, there’s a finite number of states which the game can be in. Within any unique state the game should behave differently and the application can be switched from one state to another instantaneously if the right conditions are met. To apply this concept we decided to use a State Machine, however, using one that is based on conditionals brings a lot of problems, especially when we start adding more and more states and state-dependent behaviors to the Game class. These will cause most methods to have a lot of scattered conditional logic that picks the proper behavior of a method according to the current state. Code like this violates the **Single Responsability Principle** and is very difficult to maintain because any change to the transition logic may require changing state conditionals in every method.

#### The Pattern
To solve this issue we applied the **State** pattern which suggests that you create new classes for all possible states of an object and extract all state-specific behaviors into these classes. This way, instead of implementing all behaviors on its own, the Game class now stores a reference to one of the state objects that represents its current state, and delegates all the state-related work to that object. To switch to a different state of the game we simply replace the active state object with another object that represents that new state.

#### Implementation
The following diagram shows how the pattern’s roles were assigned to the application classes. 

![StatePattern](https://user-images.githubusercontent.com/43822403/83294756-be450b00-a1e5-11ea-9f97-8be3f23d6443.png)


These classes can be found in the following files:

- [Game](../src/main/java/controller/Game.java)

- [GameState](../src/main/java/controller/state/GameState.java)

- [BuggySelectionState](../src/main/java/controller/state/BuggySelectionState.java)

- [GameOverState](../src/main/java/controller/state/GameOverState.java)

-  [MenuState](../src/main/java/controller/state/MenuState.java)

-  [PauseState](../src/main/java/controller/state/PauseState.java)

-  [PlayState](../src/main/java/controller/state/PlayState.java)

- [ScoresState](../src/main/java/controller/state/ScoresState.java)

#### Consequences
The implementation of the State Pattern in the current design presents the following benefits:

- It simplifies the code of the context by eliminating bulky state machine conditionals and instead uses polymorphism to activate the right behavior.

- The several states that represent the game's state become explicit in the code, instead of relying on a series of flags.

- By organizing the code related to particular states into separate classes, the design now respects the **Single Responsability Principle**.

- Finally, similarly to the previously explained Factory Method pattern, it follows the **Open/Closed Principle**, since it allows the introduction of new states without changing existing state classes or the context.


## Known Code Smells and Refactoring Suggestions
### Switch statements 
We use switch statements, as well as if clauses to draw the elements of the interface ([MainMenuView](../src/main/java/view/MainMenuView.java) and [BuggyView](../src/main/java/view/BuggyView.java)). 
- In MainMenuView, the if clauses are used to highlight the selected option. We could not find a way to improve this code.
 - In BuggyView, the switch statements are used to allow different behaviours (animations) between different buggy types. This segment of the code could be improved by making the Buggy class abstract and implementing different subclasses that extend this class. This way,  we would not need the switch statements that we currently have in the code. This refactoring is called Replace Type Code with Subclasses.

### Use of instanceof
We use the instanceof function on the [RandomEnemyCreator](../src/main/java/controller/factory/RandomEnemyCreator.java) class, as a way to make sure that we don't have two enemies of the same type simultaneously being displayed on the screen. We could get around this issue by adding a static id to the classes that implements Enemy (Alien and Robot), however we chose to leave this instanceof call, since it is the only use of this function in the project.

### Type casting
We use casting in the [AlienView](../src/main/java/view/AlienView.java) class, as a way to draw the lasers fired by the Alien, (in the method drawAlienLasers method). This smell could be treated by implementing the same drawAlienLasers for the Robot class, that would return null. Since the Robot does not fire lasers, it adopts the default behaviour, returning null. This refactoring is called "Introduce null object".


## Testing
All packages (Model, View and Controller) were properly tested using several unit tests, the coverage and mutation testing report can be found [here](../docs/test).

![test_result](https://user-images.githubusercontent.com/43822403/83336740-dda76b00-a2ad-11ea-8d9b-2d477926b82a.png)
## Self-Evaluation
The work was evenly split between the two members of the group.

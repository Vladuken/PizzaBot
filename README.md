# PizzaBot

Kotlin Console application for calculating movement commands for PizzaBot.

## How to run:

To run application: 
```
 ./gradlew run --args='5x5 (0,0) (1,3) (4,4) (4,2) (4,1)'
```

<p>First parameter - 5x5 - is Grid `width` x `height` parameters.</p>

<p>Next argument - list of Points: (`x coordinate`,`y coordinate`). 
Each Point should be separated one from another with whitespace character.</p>


## Code structure:

Main entities of application:

#### Interfaces
- `IPizzaBot` - interface for calculating path for given **Grid** and **Points**. Returns list of **Command**.
- `IPointOrderCalculator` - interface for calculating order in which **PizzaBot** will process and deliver pizzas. Returns list of **Points**.
- `IMoveStrategy` - between each 2 points PizzaBot can move differently. For example Bot can move firstly in X-axis, and
then in Y-axis, or vice versa (or some other strategies). This interface calculates movements between 2 concrete **Points**
and return list of **Command**.

#### Model classes

- `Command` - sealed class to define basic set of movement that PizzaBot supports. For now, it
is **MoveNorth**, **MoveSouth**, **MoveEast**, **MoveWest**, and **DropPizza**
- `Grid` - data class to contain data about Grid. X-width and Y-width.
- `Point` - data class to contain data about location. X-coordinate and Y-coordinate.

#### Console parsing
- `IArgsParser` - interface for parsing input console arguments into **Grid** and list of **Points**. Also, **IArgsParser** can return output with errors:
  - `EmptyError` - when no arguments provided
  - `GridError` - when **Grid** argument provided incorrectly
  - `PointsError`- when **Point** list argument provided incorrectly
  - `PointsOutOfBoundsError` - when **Points** arguments provided are out of bound of **Grid** argument. 
    

## Testing 
For each interface I've provided implementation and cover it with some unit-tests.

- `ArgsParser` - implementation of `IArgsParser`. Handles parsing arguments in next format: `5x5 (3,3) (5,1) (1,2)`
- `XYMoveStrategy` - implementation of `IMoveStrategy`. Handles Move strategy when Bot move between points firstly in X-axis and lately in Y-axis.
- `DefaultPointOrderCalculator` - implementation of `IPointOrderCalculator`. Returns given list without sorting it in any way.
- `SmartPizzaBot` - implementation of `IPizzaBot`. You can provide different `IMoveStrategy`, `IPointOrderCalculator` or event initial Point of the Bot.

Each of these implementations is tested in corresponding test files.

To run tests:
```
./gradlew test
```
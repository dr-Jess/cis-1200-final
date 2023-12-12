=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: jessez
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Inheritance and Subtyping
    - I made use of inheritance to organize the variety of items and tiles that can be found on the game board.
    - There are a wide variety of different objects, but they share some common attributes (checking whether solid, etc.)
    - I made use of interfaces since the objects within each interface behaved wildly differently, so an abstract class would not make any sense (no implementations to inherit)
    - Additionally, they have enough in different to justify this approach (e.g. spikes damage players each round, while enemies can be kicked around and destroyed)
    - Clearly different implementation of collide and objectOnTile methods
    - Used dynamic dispatch, cast all objects to Tiles/Items in internal representations and called shared methods

  2. JUnit Testable Component
    - I wrote the entire backend of this game independently of the frontend; like many real world apps, the frontend is just a display/control shell sending signals to the backend
    - JUnit tests were written
    - Variety of backend functions separately testable (moving, requests to get info, the individual responses by Objects to certain actions)
    - I'd say tests are pretty decent in completeness; 100% coverage of Helltaker class with consideration of many edge cases

  3. File I/O
    - Stored saves are in files that persist regardless of game
    - Stores entire game state; can fully recover or save everything about the game at once
    - Exceptions simply displayed as a status to user, no crashing
    - Input files are parsed and handled correctly (as will be seen)
    - Able of accurately writing to file and recalling information from file

  4. 2D Arrays
    - Used two 2D arrays to represent grid of tiles and items
    - Iteration through 2D arrays to handle updating/searching
    - 2D arrays are seprate since tiles/objects on top are separate
    - Encapsulation is maintained and tested
    - 2D arrays are a clear choice given the map is literally a grid
    - 2D arrays contain Tile and Item, complex types are more storage than primitives but the objects are fairly complex and
      often need to store their own information (ex. altspikes storing current state, etc0

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  - enums package
    - Contains enums to store values for communication between classes. Pretty self explanatory:
      - Action: the different actions that occur when an item is on a tile
      - Collision: the interactions that occur when a player kicks an objects
      - Direction: up/down/left/right
      - GameObjs: an interface between the Items and Tiles enums so that they can be used together (array of GameObjs enums)
      - GameStatus: the different states of a game won/lost/playing
      - Items: Each type of item, along with methods for the associated symbol when stringified and a filename for the image
      - Tiles: Likewise as above for all types of tiles
  - objects package
    - Contains interfaces and implemented objects for the game
    - Interfaces:
      - GameObj: all objects implement this interface. Has getEnum (to get the GameObjs enum), isSolid (whether the object is solid), and nextTurn (what happens at the start of each turn)
      - Item: all items implement this interface. Has a collide method to handle player interaction.
      - Tile: all tiles implement this interface. Has an objectOnTile method to handle item interaction.
    - Items:
      - Enemy: can be kicked around and destroyed if kicked against solid object
      - Lock: acts like a kickable wall until interacted with while having a key, at which point it disappears
      - Player: the player
      - Rock: can be kicked around like enemies, but cannot be destroyed
    - Tiles:
      - Wall: Uninteractable solid wall around the game
      - Floor: Uninteractable/it's literally just the floor
      - Goal: Special floor tile that ends the game with a win if stepped on
      - Key: Special floor tile that gives the player a key if stepped on
      - Spike: Special floor tile that removes a move from the player if the player is on it at the start of each round
      - AltSpike: Same as above but alternating being active (removing a turn) and inactive (doing nothing) every turn
  - GameBoard
    - Very similar to preprovided GameBoard in tictactoe:
      - Handles keyboard interaction with game
      - Handles mouse clicks to regain focus on game
      - Uses backend methods to display game state
      - Uses backend methods to keep status updated
      - Interacts with backend to load/save filenames provided by user
  - Helltaker
    - The engine of the backend for this game
      - Stores current gamestate with 2D arrays and a few fields
      - Has getters to get information about the gamestate (board layouts, turns, status)
      - Allows user to move player with a method call and handles associated logic
  - LevelIO
    - Handles all file IO for the game
    - Static method for saving a gamestate to a file
    - Constructor can take either a filename or a reader, parses and validates data
    - New instance can be used to retrieve data for creating a new Helltaker instance (loading game)
  - RunHelltaker
    - Creates control panel, status bar, game etc. very similar to original provided
    - Creates popup instruction panel
  - HelltakerTest
    - JUnit Tests for backend

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  - I didn't think to use the enums for Tile/Item representation, so some methods
    behaviours were a bit messy and it probably would've been cleaner if I'd thought
    of this from the start

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  - The design isn't perfect, and there probably are optimization to be made. I'd say
    I did well separating frontend/backend functionality, although I could've broken backend
    down further into smaller units. Private state is pretty well encapsulated, although
    I probably could've written deeper validation of input.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

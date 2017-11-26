## My Trainer And I 

_MTAI_ was created as an Android tool to help health and fitness trainers deliver custom, individualized exercise programs for their clients.  This tool provides the trainer with a list of exercises to choose from with just a few clicks.  Since this tool runs
on the client's phone, it provides the client with easy access to their program and an ability to capture their progress.

### Functionality

With the convenience of selecting from a database of exercises for each body group, the trainer has the ability to point and click in 
order to build a customized exercise plan.  Also, he can adjust the sets and reps to fit the clients goal.  

For the client, they have the ease of pulling up their plan for each day of the week and the ability to capture the last heaviest 
weight they were able to complete.  By logging their weight, bmi and fat percentage, their progress is easily 
viewable by selecting the graph button available on the client screen, which will display their weight, body fat percentage and 
bmi progress.

### Android OS version and hardware application testing
* Nexus 5X API 22 Emulator
* Nexus 5X API 22 Emulator Landscape view: prevents listview from displaying on Trainer page.
* SAMSUNG Android SM-G900V v6.0.1
  
### Application State and Action Items

MTAI is a viable Android application that runs successfully on the Nexus 5X API 22 Emulator .  It does need some validation 
checks that cause it to accept selections where data is missing.  In some cases it causes the application to stop running.  
Below is a list of those validation components that need to be built in.

Application needs (listed in priority order):

* Trainer View:
  1) When scrolling list after checkbox is selected, check is lost (functional).
  2) After selection of checkbox, confirm a day of week radio button was selected before it can save (functional).
  3) When selecting cardio, the sets and reps need to be minutes and level (cosmetic).
  
* Client Stats View:
  1) Validate entries when any of the fields are not filled out, but save is selected. Now it stops app (functional).
  2) Add Left axis label (cosmetic).
  3) Adjust graph to make graphed lines appear closer together, for a more intuitive display (cosmetic).  
  
  
* Day's Schedule View:
  1) Incorporate sets and reps validation range such as sets 1-5 and reps 1-25 (cosmetic). 

### Stretch Goals:
1) Home page could include a page that captures the users eye and draws interest.
2) Provide link on how to perform exercise through videos or description of proper form.

## Authors

* Caryl Stuart** - *Initial Developer* 

## Acknowledgments

* Nick Bennett
* Chris Hughes
* Trainer Steven Sanchez 

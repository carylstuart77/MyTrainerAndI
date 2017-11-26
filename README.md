## My Trainer And I 

_MTAI_ was created as an Android tool to help health and fitness trainers deliver custom, individualized exercise programs for their clients.  This tool provides the trainer with a list of exercises to choose from with just a few clicks.  Since this tool runs
on the clients phone, it provides the client with easy access to their program and an ability to capture their progress.

### Functionality

With the convenience of selecting from a database of exercises for each body group, the trainer has the ability to point and click in 
order to build a customized exercise plan.  Also, he can adjust the sets and reps to fit the clients goal.  

For the client, they have the ease of pulling up their plan for each day's workout and the ability to capture the last heaviest 
weight they were able to complete.  Also, they can keep track of their weight, bmi and fat percentage.  As a stretch goal their 
weight and body fat percentage can be displayed in graph form to visualize their progress.

### Android OS version and hardware application testing
Nexus 5X API 22 Emulator
Nexus 5X API 22 Emulator Landscape view prevents listview from displaying on Trainer page.
SAMSUNG Android 
  
### Application State and Action Items

MTAI is a viable Android application that runs sucesfully.  It does need some validation checks that cause it to accept
selections when not all the data has been selected.  In some cases it causes the application to stop running.  Below is
a list of those components that need to be built int.

Application needs (listed in priority order):

* Trainer Screen:
  1) When scrolling list after checkbox is selected, check is lost.
  2) After selection of checkbox, confirm a day of week radio button was selected before it can save.
  3) When selecting cardio, the sets and reps need to be minutes and level.
  
* Client Stats:
  1) Validate entries when any of the fields are not filled out, but save is selected. Now it stops app.
  2) Adjust graph to make it look better.  Add Left axis label. 
  
  * Day's Schedule:
  1) Provide range of sets and reps range validation(Sets 1-5, Reps 1-25);

### Stretch Goals/Cosmetic additions:
1) On home page include a page that captures the users eye and draws interest.
2) Provide link to how to videos.

## Authors

* Caryl Stuart** - *Initial Developer* 

## Acknowledgments

* Nick Bennett
* Chris Hughes
* Trainer Steven Sanchez 

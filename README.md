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

MTAI is a viable Android application that runs successfully on the Nexus 5X API 22 Emulator .  I've added data validation checks,
but I have found a few more that will be added prior to demo day.  Below is a list of those validation components that need to be
built in.

Application needs (listed in priority order):
   
* Trainer View:
  1) When scrolling list after checkbox had been selected, checkbox selection is lost (functional).
  2) Add message to Trainers Master Plan screen to inform user they have not selected any exercises upon save (functional).
  3) When selecting Cardio, the sets and reps need to be level and minutes, instead of sets and reps (cosmetic).
  
* Client Stats View:
  1) Add Left axis label (cosmetic).
  2) Adjust graph to make graphed lines appear closer together, for a more intuitive display (cosmetic).   
  
* Day's Schedule View:
  1) Incorporate sets and reps validation range such as sets 1-5 and reps 1-25 (cosmetic). 
  
  * Navigation Bar/top settings:
  1)  Remove settings option.  Does not perform any funtion (function/cosmetic).

### Stretch Goals:
1) Provide Trainer with the capability to unselect previous days exercise selections.  
   Currently, it resets and removes all exercerises by day.
2) Home page could include a page that captures the users eye and draws interest.
3) Provide link on how to perform exercise through videos or description of proper form.

## Javadocs
[Javadocs link](docs)

## Authors

* Caryl Stuart** - *Initial Developer* 

## Acknowledgments

* Nick Bennett
* Chris Hughes
* Trainer Steven Sanchez 

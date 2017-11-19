## My Trainer And I 

_MTAI_ was created as an Android tool to help health and fitness trainers deliver custom, individualized exercise programs for their clients.  This tool provides the trainer with a list of exercises to choose from with just a few clicks.  Since this tool runs
on the clients phone, it provides the client with easy access to their plan and an ability to capture their progress.

### Functionality

With the convenience of selecting from a database of exercises for each body group, the trainer has the ability to point and click in 
order to build a customized exercise plan.  Also, he can adjust the sets and reps to fit the clients goal.  

For the client, they have the ease of pulling up their plan for each day's workout and the ability to capture the last heaviest 
weight they were able to complete.  Also, they can keep track of their weight, bmi and fat percentage.  As a stretch goal their 
weight and body fat percentage can be displayed in graph form to visualize their progress.
  
### Action Items

Application needs (listed in priority order):
* Insert data into database from Excel db source for exercise table.

* Trainer Screen:
  1) Validate Sets and Reps entries; If nothing entered, default to 4 Sets and 12 Reps.
  2) Validate Day of week is selected.
  3) Enable Save Button to updated DB (.
  4) Delete entries in DB for that day and insert new entry for that day.
  
* Day's Schedule:
  1) Add Save Button and enable it to update Day's Sets, Reps and lbs.
  2) When Sets and Reps fields are selected blank them out and accept new entries.
  3) Validate entries (Sets 1-5, Reps 1-25);
  4) Auto close keyboard.
  
* Client Stats:
  1) Enable a view of the clients Weight, BMI and Fat % over time. (New Fragment)
  2) Disable Client Name and Height, if client name exists in db. Throw 

### Stretch Goals
* Client Stats:
  1) Graph Weight and Fat percentage.

## Authors

* Caryl Stuart** - *Initial Developer* 

## Acknowledgments

* Nick and Chris**
* Trainer Steven Sanchez ** 

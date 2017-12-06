package edu.cnm.deepdive.mytrainerandi.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import edu.cnm.deepdive.mytrainerandi.R;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import java.util.List;


/**
 * Trainer3 adapter to prepare view of rows for the days scheduled exercises. Adapter object acts as
 * a bridge between an AdapterView and the underlying data for that view.
 */
public class Trainer3ListAdapter extends ArrayAdapter<ExerciseByDay> {

  LayoutInflater inflater = LayoutInflater.from(getContext());

  /**
   * Convert the array of the master list of exercise into scrollable view for selection by
   * trainer.
   */

  public Trainer3ListAdapter(Context context, int resource,
      List<ExerciseByDay> objects) {
    super(context, resource, objects);
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {

    if (view == null) {
      view = inflater.inflate(R.layout.trainer3_listview, parent, false);
    }

    TextView tcircuit = view.findViewById(R.id.trainer_circuit);
    TextView tmuscle = view.findViewById(R.id.trainer_muscle);
    TextView texercise = view.findViewById(R.id.trainer_exercise);

    EditText esets = view.findViewById(R.id.edit_trainersets);
    EditText ereps = view.findViewById(R.id.edit_trainerreps);
    CheckBox cbox = view.findViewById(R.id.edit_trainerpick);

    //get position of checked box.
    final ExerciseByDay item = getItem(position);

    cbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        item.setSelected(b);
      }
    });

    esets.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        if (!editable.toString().equals("")) {
          item.setSets(Integer.parseInt(editable.toString()));
        }
      }
    });
    ereps.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        if (!editable.toString().equals("")) {
          item.setReps(Integer.parseInt(editable.toString()));
        }
      }
    });

    if (item.isSelected()) {
      cbox.setChecked(true);
      ereps.setText("" + item.getReps());
      esets.setText("" + item.getSets());
    }else {
      cbox.setChecked(false);
      ereps.setText("");
      esets.setText("");
    }

    tcircuit.setText(item.getExercise().getCircuit());
    tmuscle.setText(item.getExercise().getMuscle());
    texercise.setText(item.getExercise().getExercisename());

    return view;
  }

}

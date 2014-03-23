package be.ac.ucl.lfsab1509.memogeo;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class ChooseTime extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{	
	private int parentActivity;
	
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
	
	public void putParentActivity(int x)
	{
		parentActivity = x;
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) 
	{

	    String min = minute+"";
		
	    if (minute < 10)
		{
			min = "0"+minute;
		}
		
		String hour = hourOfDay+"";
		
		if (hourOfDay < 10)
		{
			hour = "0"+hourOfDay;
		}
		
		// Generate the string Time for the optionsActivity class
		if (parentActivity == 1)
		{
			((OptionsActivity) getActivity()).EditTextTime(hour+":"+min);
		}
		else
		{
			((TimersActivity) getActivity()).EditTextTime(hour+":"+min);
		}
	}

}

package be.ac.ucl.lfsab1509.memogeo;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;


public class ChooseDate extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
	
	private int parentActivity; 
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
	{
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

	public void putParentActivity(int x)
		{
			parentActivity = x;
		}
	
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) 
	{
		month++;
		
		String mon = month+"";
		
	    if (month < 10)
	    {
			mon = "0"+month;
		}
		
		String days = day+"";
		
		if (day < 10)
		{
			days = "0"+day;
		}
		
		// Generate the string Date for the optionsActivity class
		//((OptionsActivity) getActivity()).EditTextDate(days+"/"+mon+"/"+year);
		
		if (parentActivity==1)
				    {
						((OptionsActivity) getActivity()).EditTextDate(days+"/"+mon+"/"+year);
				    }
					else
					{
						((TimersActivity) getActivity()).EditTextDate(days+"/"+mon+"/"+year);
					}
	}
}

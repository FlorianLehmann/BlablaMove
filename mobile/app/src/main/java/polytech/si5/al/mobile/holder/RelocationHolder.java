package polytech.si5.al.mobile.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.business.Relocation;

public class RelocationHolder extends RecyclerView.ViewHolder {

    private TextView fromText;
    private TextView toText;
    private TextView volume;

    private TextView depatureCalendar;
    private TextView arrivalCalendar;

    public RelocationHolder(View itemView) {
        super(itemView);

        fromText = itemView.findViewById(R.id.textFromRelocation);
        toText = itemView.findViewById(R.id.textToRelocation);
        volume = itemView.findViewById(R.id.textVolumeRelocation);
        depatureCalendar = itemView.findViewById(R.id.textDepartureRelocation);
        arrivalCalendar = itemView.findViewById(R.id.textArrivalRelocation);

        Random rnd = new Random();
        itemView.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
    }

    public void bind(Relocation relocation){
        fromText.setText(relocation.getFrom());
        toText.setText(relocation.getTo());
        volume.setText("" + relocation.getVolume());

        depatureCalendar.setText(convertCalendarToString(relocation.getDeparture()));
        arrivalCalendar.setText(convertCalendarToString(relocation.getArrival()));
    }

    private String convertCalendarToString(Calendar departure) {
        String strdate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

        if (departure != null) {
            strdate = sdf.format(departure.getTime());
        }

        return strdate;
    }
}

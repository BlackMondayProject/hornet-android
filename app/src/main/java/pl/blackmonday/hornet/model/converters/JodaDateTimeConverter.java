package pl.blackmonday.hornet.model.converters;

import android.os.Parcel;

import org.joda.time.DateTime;
import org.parceler.converter.NullableParcelConverter;

/**
 * Created by Marcin Laskowski on 26.10.16.
 * Senfino 2016
 */

public class JodaDateTimeConverter extends NullableParcelConverter<DateTime> {

    @Override
    public void nullSafeToParcel(DateTime input, Parcel parcel) {
        parcel.writeLong(input.getMillis());
    }

    @Override
    public DateTime nullSafeFromParcel(Parcel parcel) {
        return new DateTime(parcel.readLong());
    }

}

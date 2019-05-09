package th.ac.rmutsv.bigpicture;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private String Email, Image, Name;

    public UserModel() {
    }

    public UserModel(String email, String image, String name) {
        Email = email;
        Image = image;
        Name = name;
    }

    protected UserModel(Parcel in) {
        Email = in.readString();
        Image = in.readString();
        Name = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Email);
        dest.writeString(Image);
        dest.writeString(Name);
    }
}

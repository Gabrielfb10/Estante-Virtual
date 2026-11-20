package main.java.estantevirtual.model;
import estantevirtual.model.MediaModel;
import main.java.estantevirtual.model.enums.ReadingStatusEnum;

public class UserMediaModel {
    private Long id;
    private UserModel user;
    private MediaModel media;
    private int progress;
    private int rewardedProgress;
    private double rating;
    private ReadingStatusEnum status;

    public UserMediaModel (Long id, UserModel user, MediaModel media) {
        this.id = id;
        this.user = user;
        this.media = media;
        this.progress = 0;
        this.rewardedProgress = 0;
        //A nota pertence a [0,5]
        this.rating = -1.0;
        this.status = ReadingStatusEnum.PLANNED;
    }

    public UserModel getUser () { return user; }

    public MediaModel getMedia () { return media; }

    public int getProgress () { return progress; }

    public int getRewardedProgress () { return rewardedProgress; }

    public double getProgressPercentage () { return (progress/media.getTotalUnits()); }

    public void setProgress (int progress) { this.progress = progress; }

    public void setRating (double rating) { this.rating = rating; }

    public void markRewardedProgress () { rewardedProgress = Math.max(rewardedProgress, progress); }

    public void setStatus (ReadingStatusEnum status) { this.status = status; }

}

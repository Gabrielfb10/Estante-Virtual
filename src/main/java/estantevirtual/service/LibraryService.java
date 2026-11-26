package estantevirtual.service;

import estantevirtual.strategy.XPCalculator;
import estantevirtual.model.MediaModel;
import estantevirtual.model.UserMediaModel;
import estantevirtual.model.UserModel;
import estantevirtual.model.enums.ReadingStatusEnum;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

public class LibraryService {

    private XPCalculator bookCalculator;
    private XPCalculator AudioBookCalculator;
    private long nextUserMediaId = 1L;

    private record HistoryKey(Long userId, Long mediaId) {}
    private final Map<HistoryKey, Integer> rewardedHistory = new HashMap<>();

    public UserMediaModel addMedia(UserModel user, MediaModel media){
        if (user == null || media == null) {
            throw new IllegalArgumentException("Informe o usuário e a obra.");
        }

        for(UserMediaModel item : user.getShelf()){
            if (Objects.equals(item.getMedia().getId(), media.getId())){
                throw new IllegalArgumentException("Essa obra já existe na sua estante!");
            }
        }
        HistoryKey key = new HistoryKey(user.getId(), media.getId());
        int rewardedProgress = rewardedHistory.getOrDefault(key, 0);

        UserMediaModel userMedia = new UserMediaModel(nextUserMediaId++,user, media);
        user.addToShelf(userMedia);
        
        return userMedia;
    }

    public void removeMedia(UserMediaModel userMedia){
        if (userMedia == null){
            throw new IllegalArgumentException("Informe uma obra para remover");
        }
        UserModel user = userMedia.getUser();

        if(!user.getShelf().contains(userMedia)){
            throw new IllegalArgumentException("A obra não está na sua estante!");
        }

        HistoryKey key = new HistoryKey(user.getId(), userMedia.getMedia().getId());
        rewardedHistory.merge(key, userMedia.getRewardedProgress(), Math::max);

        user.removeFromShelf(userMedia);
    }

    public void updateProgress(UserMediaModel userMedia, int progress) {
    }

    public void rateMedia(UserMediaModel userMedia, double rating) {
    }

    public void changeStatus(UserMediaModel userMedia, ReadingStatusEnum status) {
    }

    private XPCalculator selectXPCalculator(MediaModel media) {
        return null;
    }
}

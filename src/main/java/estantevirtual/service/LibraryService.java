package estantevirtual.service;

import estantevirtual.model.MediaModel;
import estantevirtual.model.UserMediaModel;
import estantevirtual.model.UserModel;
import estantevirtual.model.enums.ReadingStatusEnum;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

public class LibraryService {

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

        UserMediaModel userMedia = new UserMediaModel(nextUserMediaId++, user, media, rewardedProgress);
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

    //Atualiza o progresso, não dando mais xp caso o novo progresso seja menor que o registrado ou igual ao anterior
    public void updateProgress(UserMediaModel userMedia, int progress) {
        if (userMedia == null){
            throw new IllegalArgumentException("Informe uma obra para atualizar o progresso");
        }

        UserModel user = userMedia.getUser();

        if(!user.getShelf().contains(userMedia)){
            throw new IllegalArgumentException("A obra não está na sua estante!");
        }

        int rewardedProgress = userMedia.getRewardedProgress();

        if (progress <= rewardedProgress) {
            userMedia.setProgress(progress);
            return;
        }

        int xp = userMedia.getMedia()
                .getXpCalculator()
                .calculateXp(rewardedProgress, progress);
        userMedia.setProgress(progress);
        user.addXp(xp);
        userMedia.markRewardedProgress();
    }

    public void rateMedia(UserMediaModel userMedia, double rating) {
    }

    public void changeStatus(UserMediaModel userMedia, ReadingStatusEnum status) {
    }
}

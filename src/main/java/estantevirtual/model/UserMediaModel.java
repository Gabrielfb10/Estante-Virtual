package estantevirtual.model;
import estantevirtual.model.enums.ReadingStatusEnum;

public class UserMediaModel {
    private Long id;
    private UserModel user;
    private MediaModel media;
    private int progress;
    private int rewardedProgress;
    private Double rating;
    private ReadingStatusEnum status;

    public UserMediaModel (Long id, UserModel user, MediaModel media) {
        this(id, user, media, 0);
    }

    public UserMediaModel(Long id, UserModel user, MediaModel media, int rewardedProgress) {
        if (rewardedProgress < 0) {
            throw new IllegalArgumentException("O progresso premiado deve ser maior ou igual a zero");
        }
        this.id = id;
        this.user = user;
        this.media = media;
        this.progress = 0;
        this.rewardedProgress = rewardedProgress;
        //A nota pertence a [0,5]
        this.rating = null;
        this.status = ReadingStatusEnum.PLANNED;
    }

    public UserModel getUser () { return user; }

    public MediaModel getMedia () { return media; }

    public int getProgress () { return progress; }

    public int getRewardedProgress () { return rewardedProgress; }
    //Agora calcula a porcentagem corretamente
    public double getProgressPercentage () { return 100.0 * progress/media.getTotalUnits(); }
    //A verificação principal é feita em progress.
    public void setProgress (int progress) {
        int total = media.getTotalUnits();
        if (progress < 0 || progress > total){
            throw new IllegalArgumentException("O progresso deve estar entre zero e o total");
        }

        if (progress < this.progress){
            throw new IllegalArgumentException("O progresso não pode reduzir");
        }

        if(this.status == ReadingStatusEnum.DROPPED){
            throw new IllegalArgumentException("Retome o status da obra antes de atualizar o progresso.");
        }
        ReadingStatusEnum newStatus = this.status;

        if(progress == total){
            newStatus = ReadingStatusEnum.COMPLETED;
        } else if (progress > 0){
            newStatus = ReadingStatusEnum.IN_PROGRESS;
        }

        this.progress = progress;
        this.status = newStatus;
    }

    public void setRating (double rating) {
        if (!Double.isFinite(rating) || rating < 0 || rating > 5){
            throw new IllegalArgumentException("A nota deve ser entre 0 e 5");
        }

        this.rating = rating;
    }

    public void markRewardedProgress () { rewardedProgress = Math.max(rewardedProgress, progress); }
    //O boolean verifica se a troca é válida e só então permite a alteração de status.
    public void setStatus (ReadingStatusEnum status) {
        if (status == null){
            throw new IllegalArgumentException("Informe um status");
        }

        if (status == this.status){
            return;
        }

        boolean allowed = switch (status) {
            case PLANNED -> this.status == ReadingStatusEnum.IN_PROGRESS
                    && this.progress == 0;
            case IN_PROGRESS -> this.status == ReadingStatusEnum.PLANNED
                    || this.status == ReadingStatusEnum.DROPPED;
            case DROPPED -> this.status == ReadingStatusEnum.PLANNED
                    || this.status == ReadingStatusEnum.IN_PROGRESS;
            case COMPLETED -> false;
        };

        if (!allowed){
            throw new IllegalArgumentException("Mudança inválida!");
        }

        this.status = status;
    }

}

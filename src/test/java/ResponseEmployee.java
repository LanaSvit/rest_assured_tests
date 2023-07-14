import lombok.*;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder

//    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ResponseEmployee {
        private  String status;
        private  String message;
        private DataId data;

    }


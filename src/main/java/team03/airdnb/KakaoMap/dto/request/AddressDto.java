package team03.airdnb.KakaoMap.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {
    private Document[] documents;

    public void setDocuments(Document[] documents) {
        this.documents = documents;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Document {
        private String addressName;
        private String x;
        private String y;

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public void setX(String x) {
            this.x = x;
        }

        public void setY(String y) {
            this.y = y;
        }
    }
}

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;


public class S3Operation {
    public static void main(String[] args) {
        var s3Client = S3Client.builder().region(Region.of("cn-northwest-1")).build();

        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request
                .builder()
                .bucket("test-ad")
                .build();
        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsRequest);

        System.out.println("keyCount: " + listObjectsV2Response.keyCount());
        List<S3Object> contents = listObjectsV2Response.contents();
        if (contents != null) {
            for (S3Object content : contents) {
                System.out.println("object key: " + content.key());
            }
        }

    }
}

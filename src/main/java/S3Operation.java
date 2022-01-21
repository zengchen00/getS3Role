import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;


public class S3Operation {
    public static void main(String[] args) {
        String bucket_name = "test-ad";

        System.out.println("*********** sdk v1 ***********");
        AmazonS3 amazonS3 = AmazonS3Client.builder().withRegion("cn-northwest-1").build();
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucket_name);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        if(null != objects){
            System.out.println("v1 keyCount: " + objects.size());
            for (S3ObjectSummary os : objects) {
                System.out.println("v1 object_key: " + os.getKey());
            }
        }


        System.out.println("*********** sdk v2 ***********");
        S3Client s3Client = S3Client.builder().region(Region.of("cn-northwest-1")).build();
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request
                .builder()
                .bucket(bucket_name)
                .build();
        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsRequest);
        System.out.println("v2 keyCount: " + listObjectsV2Response.keyCount());
        List<S3Object> contents = listObjectsV2Response.contents();
        if (contents != null) {
            for (S3Object content : contents) {
                System.out.println("v2 object_key: " + content.key());
            }
        }
    }
}

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.List;


public class S3Operation {

    public static final String region = "cn-northwest-1";
    public static final String bucket_name = "test-ad";

    public static void main(String[] args) {
        testSdkV1();
//        testSdkV2();
    }

    private static void testSdkV1() {
        System.out.println("*********** sdk v1 ***********");
        AmazonS3 amazonS3 = AmazonS3Client.builder().withRegion(region).build();
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucket_name);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        if(null != objects){
            System.out.println("keyCount: " + objects.size());
            for (S3ObjectSummary os : objects) {
                System.out.println("object_key: " + os.getKey());
            }
        }
    }

//    private static void testSdkV2() {
//        System.out.println("*********** sdk v2 ***********");
//        S3Client s3Client = S3Client.builder().region(Region.of(region)).build();
//        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request
//                .builder()
//                .bucket(bucket_name)
//                .build();
//        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsRequest);
//
//        System.out.println("keyCount: " + listObjectsV2Response.keyCount());
//        List<S3Object> contents = listObjectsV2Response.contents();
//        if (contents != null) {
//            for (S3Object content : contents) {
//                System.out.println("object key: " + content.key());
//            }
//        }
//    }

}

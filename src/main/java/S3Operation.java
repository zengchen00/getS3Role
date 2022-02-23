import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;


public class S3Operation {

    public static final String region = "cn-northwest-1";
    public static final String bucket_name = "shiyou-cn-labs-analytics";

    private static final Logger log = LogManager.getLogger(S3Operation.class);

    public static void main(String[] args) {
//        testSdkV1();
        testSdkV2();
    }

//    private static void testSdkV1() {
//        System.out.println("*********** sdk v1 ***********");
//        AmazonS3 amazonS3 = AmazonS3Client.builder().withRegion(region).build();
//        ListObjectsV2Result result = amazonS3.listObjectsV2(bucket_name);
//        List<S3ObjectSummary> objects = result.getObjectSummaries();
//        if(null != objects){
//            System.out.println("keyCount: " + objects.size());
//            for (S3ObjectSummary os : objects) {
//                System.out.println("object_key: " + os.getKey());
//            }
//        }
//    }

    private static void testSdkV2() {
        System.out.println("*********** sdk v2 ***********");
        try {
            S3Client s3Client = S3Client.builder().region(Region.of(region)).build();
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request
                    .builder()
                    .bucket(bucket_name)
                    .build();
            ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsRequest);

            System.out.println("keyCount: " + listObjectsV2Response.keyCount());
            List<S3Object> contents = listObjectsV2Response.contents();
            if (contents != null) {
                for (S3Object content : contents) {
                    System.out.println("object key: " + content.key());
                }
            }
        } catch (NoSuchBucketException e) {
            // 如果 bucket 不存在，会报 NoSuchBucketException
            log.warn("bucket={} is not exsit ", bucket_name);
        } catch (AwsServiceException | SdkClientException e) {
            log.error("doesBucketExist error", e);
        }
    }

}

package solution;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Example input line:
 * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
 *
 */
public class LogFileMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

	  	//turn Text object value into a string object
	  	String line = value.toString();
	  	
	  	//split by space
	  	String[] ips = line.trim().split(" ");
	  	
	  	if (ips.length > 1) {
	  		
	  		String ip = ips[0];
	  		
	  		//write to the reducer the ip address
	  		context.write(new Text(ip), new IntWritable(1));
	  		
	  	}
  }
}
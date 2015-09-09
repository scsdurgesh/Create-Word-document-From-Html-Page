import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

class reader{
	
	public String readdata(String  path) throws IOException
	{
		File file=new File(path);
		String filedata=FileUtils.readFileToString(file, "utf-8");
		Document document =Jsoup.parse(filedata);
		StringBuilder build=new StringBuilder();
		document.body().traverse(new NodeVisitor() {
			
			@Override
			public void tail(Node arg0, int arg1) {
				// TODO Auto-generated method stub
				for(Node arg2 : arg0.childNodes())
				{
					if(arg2 instanceof TextNode)
					{
						if(!((TextNode)arg2).text().trim().equals(null))
						build.append("\n"+((TextNode)arg2).text().trim());
						
					}	
				}
			}
			
			@Override
			public void head(Node arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		return build.toString();
	}
	
	public void writedata(String data,String path) throws IOException
	{
		String docpath=path+".doc";
		File file= new File(docpath);
		FileUtils.writeStringToFile(file, data, "utf-8");
	}
	
}


public class ReadAllNodeData {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String inpath="C:/Users/Durgesh/Desktop/ESMO data/Script/ESMO tools/";
		String outpath="C:/Users/Durgesh/Desktop/ESMO data/Script/output/";
		File file=new File(inpath);
		File[] folder=file.listFiles();
		
		for(int i=0; i<folder.length; i++)
		{
			reader read=new reader();
			String data=read.readdata(folder[i].getPath());
			read.writedata(data,outpath+"/"+folder[i].getName().substring(0, folder[i].getName().length()-5) );
		}
		System.out.println("Done");
	}

}

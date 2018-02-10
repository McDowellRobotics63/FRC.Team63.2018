package org.usfirst.frc.team63.robot.subsystems;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DebugSubsystem extends Subsystem {

	private final int MAX_DIRECTORY_SIZE = 5*1024*1024; //5MB
	private final int MAX_FILE_COUNT = 100;
	private final String DEBUG_DIRECTORY = "/home/lvuser/debug";
	
	private PrintWriter _pw = null;
	List<List<Double>> _data = new ArrayList<>();
	List<String> _columnHeaders = new ArrayList<>();
	
    public void initDefaultCommand() {
    }
    
    public DebugSubsystem()
    {
    	try
    	{
    		//Create the debug directory
    		//If it already exists, no worries
	    	new File(DEBUG_DIRECTORY).mkdir();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    	}    	
    }
    
    public void Start(String filename, List<String> vars)
    {
    	_columnHeaders.clear();
    	_data.clear();
    	for(int i = 0; i < vars.size(); i++)
    	{
    		_columnHeaders.add(vars.get(i));
    		_data.add(new ArrayList<>());    		    		
    	}

    	try
    	{
    		_pw = new PrintWriter(new File(DEBUG_DIRECTORY + filename + "_" + System.currentTimeMillis() + ".csv"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}    	   
    }
    
    public void Update(List<Double> values)
    {
    	if(values.size() == _data.size())
    	{
    		for(int i = 0; i < values.size(); i++)
    		{
    			_data.get(i).add(values.get(i));
    		}
    	}
    }
    
    public void Stop()
    {
    	this.ManageDirectory();
    	
		if(_pw!= null)
		{
			StringBuilder sb = new StringBuilder();
			
			//Build the column header line
			for(int i = 0; i < _columnHeaders.size() - 1; i++)
			{
				sb.append(_columnHeaders.get(i) + ", ");
			}
			sb.append(_columnHeaders.get(_columnHeaders.size() - 1) + "\r\n");
			
			for (int i = 0; i < _data.get(0).size(); i++) {
				for (int j = 0; j < _data.size() - 1; j++) {
					sb.append(_data.get(j).get(i) + ", ");				
				}
				sb.append(_data.get(_data.size() - 1).get(i) + "\r\n");
			}
	        
		    _pw.write(sb.toString());	
			_pw.close();
			_pw = null;
		}    	
    }
    
    private void ManageDirectory()
    {
    	long fileSizeSum = 0;
    	int fileCount = 0;
    	
    	try
    	{
    		//Get list of all files in the debug directory
        	File folder = new File(DEBUG_DIRECTORY);
        	File[] listOfFiles = folder.listFiles();
        	
        	//Sort them newest to oldest by modified date
        	Arrays.sort(listOfFiles, new Comparator<File>(){
        	    public int compare(File f1, File f2)
        	    {
        	        return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
        	    } });
        	
        	//Loop through files
        	for (int i = 0; i < listOfFiles.length; i++) {
        		if (listOfFiles[i].isFile()) {        			        		
        			
        			//Sum file sizes until we reach our limit
        			if(fileSizeSum < MAX_DIRECTORY_SIZE && fileCount < MAX_FILE_COUNT)
        			{
        				fileCount++;
        				fileSizeSum = fileSizeSum + listOfFiles[i].length();
        				
        				//Did this one take us over the limit?
        				if(fileSizeSum > MAX_DIRECTORY_SIZE)
        				{
        					listOfFiles[i].delete();
        				}
        			}
        			else
        			{
        				//Start deleting files once the limit is reached
        				listOfFiles[i].delete();
        			}
        		}
        	}
        	
        	//Should be left with newest files
        	//which keep us under directory size limit
        	//and file count limit
    	} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }
}


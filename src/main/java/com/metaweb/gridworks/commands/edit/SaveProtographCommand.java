package com.metaweb.gridworks.commands.edit;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.metaweb.gridworks.commands.Command;
import com.metaweb.gridworks.model.AbstractOperation;
import com.metaweb.gridworks.model.Project;
import com.metaweb.gridworks.model.operations.SaveProtographOperation;
import com.metaweb.gridworks.process.Process;
import com.metaweb.gridworks.protograph.Protograph;

public class SaveProtographCommand extends Command {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Project project = getProject(request);
			
			String jsonString = request.getParameter("protograph");
			JSONObject json = jsonStringToObject(jsonString);
			Protograph protograph = Protograph.reconstruct(json);
			
			AbstractOperation op = new SaveProtographOperation(protograph);
			Process process = op.createProcess(project, new Properties());
			
			boolean done = project.processManager.queueProcess(process);
			
			respond(response, "{ \"code\" : " + (done ? "\"ok\"" : "\"pending\"") + " }");
			
		} catch (Exception e) {
			respondException(response, e);
		}
	}
}
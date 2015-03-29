package starlight;

import java.util.ArrayList;



public class ModuleTree {
	private String tree;
	private int version;
	private byte startIndex;
	private ArrayList<String> modules;

	public ModuleTree(String tree) {
		this.tree = tree;
		String[] mdls = tree.split(";");
		version = Integer.parseInt(mdls[0]);
		startIndex = Byte.parseByte(mdls[1]);
		modules = new ArrayList<String>();

		for (int i = 2; i < mdls.length; i++) {
			modules.add(mdls[i]);
		}
	}

	public String getTree() {
		return tree;
	}

	public int getVersion() {
		return version;
	}

	public String getModule(int index) {
		return modules.get(index - startIndex);
	}

	public int getIndex(String module) {
		for (int i = 0; i < modules.size(); i++) {
			if (modules.get(i).equals(module)) {
				return i + startIndex;
			}
		}

		return -1;
	}
}
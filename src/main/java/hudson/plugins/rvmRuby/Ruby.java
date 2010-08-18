package hudson.plugins.rvmRuby;

import hudson.Extension;
import hudson.FilePath;
import hudson.model.Descriptor;
import hudson.tasks.Builder;
import hudson.tasks.CommandInterpreter;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Invokes the ruby interpreter and invokes the Ruby script entered on the hudson build configuration.
 * It is expected that rvm is available in PATH.
 *
 * @author Vivek Pandey
 * @author Darcy Laycock
 */
public class Ruby extends CommandInterpreter {

  protected String rubyString;

  private Ruby(String command) {
    super(command);
    setRubyString("default");
  }

  private Ruby(String command, String rubyString) {
    super(command);
    setRubyString(rubyString);
  }

  protected String[] buildCommandLine(FilePath script) {
    return new String[] {"rvm", "--with-rubies", rubyString, "exec", "ruby", "-v", script.getRemote()};
  }

  protected String getContents() {
    return command;
  }

  protected String getFileExtension() {
    return ".rb";
  }

  public String getRubyString() {
    if(this.rubyString == null || this.rubyString.equals("")) {
      return "default";
    } else {
      return this.rubyString;
    }
  }

  public void setRubyString(String rs) {
    if(rs == null || rs.equals("")) rs = "default-with-rvmrc";
    this.rubyString = rs;
  }

  @Override
  public Descriptor<Builder> getDescriptor() {
    return DESCRIPTOR;
  }

  @Extension
  public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

  public static final class DescriptorImpl extends Descriptor<Builder> {
    private DescriptorImpl() {
      super(Ruby.class);
    }

    @Override
    public Builder newInstance(StaplerRequest req, JSONObject formData) {
      return new Ruby(formData.getString("ruby"), formData.getString("rubyString"));
    }

    public String getDisplayName() {
      return "Execute Ruby script (via a RVM Ruby)";
    }

    @Override
    public String getHelpFile() {
      return "/plugin/rvmRuby/help.html";
    }
  }
}

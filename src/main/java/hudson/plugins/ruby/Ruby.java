package hudson.plugins.ruby;

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

    private String ruby_string;

    private Ruby(String command) {
      super(command);
      setRubyString("default");
    }

    private Ruby(String command, String ruby_string) {
      super(command);
      setRubyString(ruby_string);
    }

    protected String[] buildCommandLine(FilePath script) {
        return new String[]{"rvm", "--with-rubies", ruby_string, "exec", "ruby", "-v", script.getRemote()};
    }

    protected String getContents() {
        return command;
    }

    protected String getFileExtension() {
        return ".rb";
    }
    
    public String getRubyString() {
      if(this.ruby_string == null || this.ruby_string.equals("")) {
        return "default";
      } else {
        return this.ruby_string;
      }
    }
    
    public void setRubyString(String rs) {
      if(rs == null || rs.equals("")) rs = "default";
      this.ruby_string = rs;
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
            return new Ruby(formData.getString("ruby"), formData.getString("ruby_string"));
        }

        public String getDisplayName() {
            return "Execute Ruby script using a RVM Ruby";
        }

        @Override
        public String getHelpFile() {
            return "/plugin/ruby/help.html";
        }
    }
}

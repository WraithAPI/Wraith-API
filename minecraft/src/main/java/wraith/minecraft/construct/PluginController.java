package wraith.minecraft.construct;

import wraith.construct.ControllerHostBase;
import wraith.construct.TickManager;

public abstract class PluginController extends ControllerHostBase
{
	public PluginController(TickManager tickManager)
	{
		super(tickManager);
	}
}

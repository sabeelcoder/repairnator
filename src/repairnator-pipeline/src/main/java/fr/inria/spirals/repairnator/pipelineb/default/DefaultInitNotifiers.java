package fr.inria.spirals.repairnator.pipeline;

import fr.inria.spirals.repairnator.LauncherUtils;
import fr.inria.spirals.repairnator.notifier.ErrorNotifier;
import fr.inria.spirals.repairnator.notifier.BugAndFixerBuildsNotifier;
import fr.inria.spirals.repairnator.notifier.PatchNotifierImpl;
import fr.inria.spirals.repairnator.notifier.engines.NotifierEngine;
import fr.inria.spirals.repairnator.notifier.AbstractNotifier;
import fr.inria.spirals.repairnator.notifier.PatchNotifier;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* Notifiers init behavior for the default use case of Repairnator */
public class DefaultInitNotifiers implements IInitNotifiers {
	private static Logger LOGGER = LoggerFactory.getLogger(DefaultInitNotifiers.class);
	protected List<AbstractNotifier> notifiers;
	protected PatchNotifier patchNotifier;

	@Override
	public void initNotifiers() {
        List<NotifierEngine> notifierEngines = LauncherUtils.initNotifierEngines(LOGGER);
        ErrorNotifier.getInstance(notifierEngines);

        this.notifiers = new ArrayList<>();
        this.notifiers.add(new BugAndFixerBuildsNotifier(notifierEngines));

        this.patchNotifier = new PatchNotifierImpl(notifierEngines);
    }

    @Override
    public List<AbstractNotifier> getNotifiers() {
    	return this.notifiers;
    }

    @Override
    public PatchNotifier getPatchNotifers() {
    	return this.patchNotifier;
    }
}
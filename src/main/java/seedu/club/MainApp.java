package seedu.club;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.club.commons.core.Config;
import seedu.club.commons.core.LogsCenter;
import seedu.club.commons.core.Version;
import seedu.club.commons.exceptions.DataLoadingException;
import seedu.club.commons.util.ConfigUtil;
import seedu.club.commons.util.StringUtil;
import seedu.club.logic.Logic;
import seedu.club.logic.LogicManager;
import seedu.club.model.ClubBook;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.ReadOnlyUserPrefs;
import seedu.club.model.UserPrefs;
import seedu.club.model.util.SampleDataUtil;
import seedu.club.storage.ClubBookStorage;
import seedu.club.storage.JsonClubBookStorage;
import seedu.club.storage.JsonUserPrefsStorage;
import seedu.club.storage.Storage;
import seedu.club.storage.StorageManager;
import seedu.club.storage.UserPrefsStorage;
import seedu.club.ui.Ui;
import seedu.club.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ClubBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ClubBookStorage clubBookStorage = new JsonClubBookStorage(
                userPrefs.getClubBookStorageFilePath());
        storage = new StorageManager(clubBookStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s club book and {@code userPrefs}. <br>
     * The data from the sample club book will be used instead if {@code storage}'s club book is not found,
     * or an empty club book will be used instead if errors occur when reading {@code storage}'s club book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getClubBookFilePath());

        Optional<ReadOnlyClubBook> clubBookOptional;
        ReadOnlyClubBook initialData;

        try {
            clubBookOptional = storage.readClubBook();

            if (!clubBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getClubBookFilePath()
                        + " populated with a sample ClubBook.");
            }
            initialData = clubBookOptional.orElseGet(SampleDataUtil::getSampleClubBook);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getClubBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty ClubBook.");
            initialData = new ClubBook();
        }

        return new ModelManager(initialData, userPrefs);
    }


    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting ClubBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ClubBook ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}

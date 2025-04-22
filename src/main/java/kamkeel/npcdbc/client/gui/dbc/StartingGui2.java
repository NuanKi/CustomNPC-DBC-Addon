package kamkeel.npcdbc.client.gui.dbc;


import JinRyuu.JRMCore.*;
import JinRyuu.JRMCore.client.minigame.MiniGameAirBoxing;
import JinRyuu.JRMCore.client.minigame.MiniGameConcentration;
import JinRyuu.JRMCore.entity.EntityAuraFlat;
import JinRyuu.JRMCore.entity.EntityNull;
import JinRyuu.JRMCore.items.ItemBarberSnC;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import cpw.mods.fml.common.FMLCommonHandler;
import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.mixins.late.IDBCGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import java.awt.*;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static kamkeel.npcdbc.util.Utility.t;

public class StartingGui2 extends GuiScreen {
    public static final int ID_APPERANCE = 0;
    public static final int ID_APPERANCE_ARCO = 19;
    public static final int ID_COLOR_PICKER = 1;
    public static final int ID_POWER_CLASS_SELECT = 2;
    public static final int ID_FINALIZING = 3;
    public static final int ID_BARBER = 8;
    public static final int ID_ATTRIBUTES = 10;
    public static final int ID_SKILLS = 11;
    public static final int ID_TECHNIQUES = 12;
    public static final int ID_ATTACK_CREATOR = 13;
    public static final int ID_DIFFICULTY = 14;
    public static final int ID_LEARN_ATTACKS_AND_JUTSUS = 15;
    public static final int ID_LEARN_SKILLS = 16;
    public static final int ID_TRAINING = 17;
    public static final int ID_MINIGAME_CONCENTRATION = 18;
    public static final int ID_NEWS = 30;
    public static final int ID_NO_CONNECTION = 31;
    public static final int ID_SERVER_INFO = 40;
    public static final int ID_UPDATE_INFO = 50;
    public static final int ID_MISSION_SYSTEM_MIN = 60;
    public static final int ID_MISSION_SYSTEM_MAX = 70;
    public static final int ID_MAIN_MISSION = 60;
    public static final int ID_SIDE_MISSION = 61;
    public static final int ID_GROUP_SYSTEM = 70;
    public static final int ID_SERVER_DONATION = 80;
    public static final int ID_CLIENT_SETTINGS = 10000;
    public static final int ID_HELP = 10001;
    public static final int ID_MINIGAME_AIRBOXING = 10002;
    public static final int ID_INSTANT_TRANSMISSION_GROUP_PICK = 10100;
    public static final int ID_NOTIFICATIONS = 10011;
    private static final int cs_mode_GRAPHIC_SETTINGS = 0;
    private static final int cs_mode_USER_INTERFACE = 1;
    private static final Logger logger = LogManager.getLogger();
    public static final String News = "News";
    public static Minecraft mc = Minecraft.getMinecraft();
    public static StartingGui2 instance;
    public MiniGameConcentration miniGameConcentration = new MiniGameConcentration();
    public MiniGameAirBoxing miniGameAirBoxing = new MiniGameAirBoxing();
    private EntityNull entityTest;
    private ArrayList<EntityAuraFlat> entityTests2;
    private Instant timer;
    public boolean guiUp = false;
    public int guiID = 0;
    public int guiIDprev = 0;
    public int guiIDprev2 = 0;
    public int guiIDprev3 = 0;
    public int guiLeft;
    public int guiTop;
    public int xSize = 256;
    public int ySize = 159;
    public float xSize_lo;
    public float ySize_lo;
    public List Modsnews;
    public int site = 0;
    public int sip = 0;
    private int diff = 0;
    private int attCrtPg = 0;
    private int attViwPg = 0;
    private int pgSkls = 0;
    private int smd;
    private int ipg;
    private int inv;
    private int lp;
    private int sscl;
    private int[] hbt;
    private int[] dfa = new int[5];
    private URI clickedURI;
    private int mcu = 0;
    public static String urlToOpen = "";
    private static float ptch = 0.0F;
    public static float scrollSide = 0.0F;
    public static int hairPreview = 0;
    public static int[] hairPreviewStates = new int[]{0, 4, 5, 6};
    private static boolean headRoton = false;
    private static int headRotX = 0;
    private static int headRotY = 0;
    private static int headRotZ = 0;
    private static final int gui_help_descs = 13;
    private static final int help_guides = 13;
    private static int help_page_id = 0;
    private static int help_mode = 0;
    private static final int[] cs_pages = new int[]{9, 3};
    private static int cs_page = 0;
    private static int cs_mode = 0;
    public static boolean CanRace = true;
    public static boolean CanGender = true;
    public static boolean CanYears = true;
    public static boolean CanHair = true;
    public static boolean CanColor = true;
    public static boolean canCustom = true;
    public static boolean CanSpecial = true;
    public static boolean CanPwr = true;
    public static boolean CanClass = true;
    private static boolean[] CanUpgrade = new boolean[]{true, true, true, true, true, true};
    public static byte EnAttNum = 0;
    public static String name = "Attack";
    public static int acquired = 1;
    public static String owner;
    public static int type;
    public static int speed;
    public static int dam;
    public static int effect;
    public static int cost;
    public static int casttime;
    public static int cooldown;
    public static int color;
    public static int density;
    public static int sincantation;
    public static int sfire;
    public static int smove;
    public static int[] techCrt;
    public static String button1;
    public static String button2;
    public static String wish;
    public static String wish2;
    public static String guiBG2;
    public static String pc;
    public static String gui_help_tabs;
    public static String wishsep;
    public static String icons;
    String SideMsn = "";
    int ss = -1;
    boolean ssb = false;
    public static int colorType;
    public static int StateSlcted;
    public static int RaceSlcted;
    public static int GenderSlcted;
    public static int YearsSlcted;
    public static int HairSlcted;
    public static int Hair2Slcted;
    public static int ColorSlcted;
    public static int BreastSizeSlcted;
    public static int SkinTypeSlcted;
    public static int BodyTypeSlcted;
    public static int BodyColPresetSlcted;
    public static int BodyColMainSlcted;
    public static int BodyColSub1Slcted;
    public static int BodyColSub2Slcted;
    public static int BodyColSub3Slcted;
    public static int FaceNoseSlcted;
    public static int FaceMouthSlcted;
    public static int EyesSlcted;
    public static int EyeColPresetSlcted;
    public static int EyeCol1Slcted;
    public static int EyeCol2Slcted;
    public static int BodyauColMainSlcted;
    public static int BodyauColSub1Slcted;
    public static int BodyauColSub2Slcted;
    public static int BodyauColSub3Slcted;
    public static int HairPrstsSlcted;
    public static boolean canSavePreset;
    public static float BrghtSlcted;
    public static int PwrtypSlcted;
    public static int ClassSlcted;
    public static boolean tail;
    public static int KiColorSlcted;
    public static ArrayList<String> PresetList;
    private static int tick;
    private static String dnsau;
    private static String dns;
    private static String dnsSent;
    private static String dnsOrig;
    private static String dnsH;
    private static String dnsHbu;
    private static String dnsHSent;
    private static String dnsHOrig;
    protected static List detailList;
    public int x = 0;
    public int y = 0;
    private byte pwr = 0;
    public int scroll;
    public int scrollMouseJump = 1;
    public boolean mousePressed;
    private int IDtoProcessConfirmFor = -1;
    private boolean confirmationWindow = false;
    private int kdf;
    private static String ssc;
    private static int sscr;
    public static boolean ufc;
    final byte HELP_MODE_SELECT = 0;
    final byte HELP_GUIDE_PICK = 1;
    final byte HELP_CRAFTING = 2;
    final byte HELP_GUIDE = 3;
    private int gui_recipe_mod = -1;
    private int gui_recipe_category = 0;
    private int text = 0;
    protected GuiTextField inputField;
    protected GuiTextField[] inputField2 = new GuiTextField[3];
    private String defaultInputFieldText = "Attack";
    private boolean inputField2Ch = false;
    private int inputField2Cl = 0;
    ModelRenderer[] hairall;
    public static int count;
    public static int warn;
    public static int startcount;
    private String Process = "Something is Wrong";
    private int wid = 0;
    private int hei = 0;
    private String textureFile = "jinryuudragonbc:sagas.png";
    private int curPage = 0;
    private int[] hcl = new int[]{0, 4, 14, 24, 40, 56};
    public static int scrlld;
    private ResourceLocation field_110351_G;
    private int panoramaTimer;
    private static final ResourceLocation[] titlePanoramaPaths;

    public boolean isGUIOpen(int id) {
        return id == this.guiID;
    }

    public StartingGui2() {
        JRMCoreGuiSliderX00.sliderValue = 0.0F;
        this.scroll = 0;
    }

    @Override
    public void initGui() {
        this.guiUp = false;
        DynamicTexture viewportTexture = new DynamicTexture(256, 256);
        this.field_110351_G = mc.getTextureManager().getDynamicTextureLocation("background", viewportTexture);

        for(int i = 0; i < this.inputField2.length; ++i) {
            this.inputField2[i] = new GuiTextField(mc.fontRenderer, 0, 0, 50, 12);
            this.inputField2[i].setText("255");
        }

        wish2 = CustomNpcPlusDBC.ID + ":textures/gui/gui.png";
        wish = CustomNpcPlusDBC.ID + ":textures/gui/gui_yellow.png";
        pc = JRMCoreH.tjjrmc + ":gui_pc.png";
        button1 = JRMCoreH.tjjrmc + ":button1.png";
        icons = JRMCoreH.tjjrmc + ":icons.png";
        instance = this;
        if (JRMCoreH.Pwrtyp == 2) {
            int type = JRMCoreH.techNCBase[3];
            int speed = JRMCoreH.techNCBase[4];
            int dam = JRMCoreH.techNCBase[5];
            int effect = JRMCoreH.techNCBase[6];
            int cost = JRMCoreH.techNCBase[7];
            int casttime = JRMCoreH.techNCBase[8];
            int cooldown = JRMCoreH.techNCBase[9];
            int color = JRMCoreH.techNCBase[10];
            int density = JRMCoreH.techNCBase[11];
            int sincantation = JRMCoreH.techNCBase[12];
            int sfire = JRMCoreH.techNCBase[13];
            int smove = JRMCoreH.techNCBase[14];
            int[] techCrtN = new int[]{1, acquired, 1, type, speed, dam, effect, cost, casttime, cooldown, color, density, sincantation, sfire, smove};
            techCrt = techCrtN;
        }

        if (JRMCoreH.Pwrtyp == 1) {
            int type = JRMCoreH.techBase[3];
            int speed = JRMCoreH.techBase[4];
            int dam = JRMCoreH.techBase[5];
            int effect = JRMCoreH.techBase[6];
            int cost = JRMCoreH.techBase[7];
            int casttime = JRMCoreH.techBase[8];
            int cooldown = JRMCoreH.techBase[9];
            int color = JRMCoreH.techBase[10];
            int density = JRMCoreH.techBase[11];
            int sincantation = JRMCoreH.techBase[12];
            int sfire = JRMCoreH.techBase[13];
            int smove = JRMCoreH.techBase[14];
            int[] techCrtN = new int[]{1, acquired, 1, type, speed, dam, effect, cost, casttime, cooldown, color, density, sincantation, sfire, smove};
            techCrt = techCrtN;
        }

        this.buttonList.clear();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int posX = this.width / 2;
        int posY = this.height / 2;
        JRMCoreGuiSliderX00.sliderValue = 0.0F;
        this.scroll = 0;
        scrollSide = 0.0F;
        this.SideMsn = "";
        this.site = 0;
        JRMCoreH.jrmct(1);
        JRMCoreH.jrmct(3);
        this.smd = 0;
        this.ipg = 0;
        this.inv = 0;
        if (JRMCoreH.Accepted == 2) {
            this.guiID = 31;
        }

        if (JRMCoreH.Accepted == 1 && (this.guiID > 50 || this.guiID < 30) && this.guiID != 8 && this.guiID != 10001 && this.guiID != 10010 && (this.guiID < 60 || this.guiID > 70) && this.guiID != 10100) {
            this.guiID = 10;
        }

        if (JRMCoreH.Accepted == 0 && this.guiID == 8) {
            this.guiID = 0;
        }

        if (this.guiID == 8) {
            if (JRMCoreH.JFC() && ItemBarberSnC.barberTarget != null) {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    dns = JRMCoreHJFC.childDNS(ItemBarberSnC.barberTarget);
                    dnsH = JRMCoreHJFC.childDNSH(ItemBarberSnC.barberTarget);
                    dnsOrig = JRMCoreHJFC.childDNS(ItemBarberSnC.barberTarget);
                    dnsHOrig = JRMCoreHJFC.childDNSH(ItemBarberSnC.barberTarget);
                    RaceSlcted = JRMCoreH.Race;
                    HairSlcted = JRMCoreH.dnsHairB(dns);
                    Hair2Slcted = JRMCoreH.dnsHairF(dns);
                    ColorSlcted = JRMCoreH.dnsHairC(dns);
                }
            } else {
                dns = JRMCoreH.dns;
                dnsH = JRMCoreH.dnsH;
                dnsOrig = JRMCoreH.dns;
                dnsHOrig = JRMCoreH.dnsH;
                RaceSlcted = JRMCoreH.Race;
                HairSlcted = JRMCoreH.dnsHairB(dns);
                Hair2Slcted = JRMCoreH.dnsHairF(dns);
                ColorSlcted = JRMCoreH.dnsHairC(dns);
            }
        }

        if (this.guiID == 0) {
            RaceSlcted = JRMCoreH.Race;
            if (JRMCoreH.dns.length() > 3) {
                dns = JRMCoreH.dns;
            } else {
                setchangerace();
            }

            if (JRMCoreH.dnsH.length() > 3) {
                dnsH = JRMCoreH.dnsH;
            }

            dnsOrig = JRMCoreH.dns;
            dnsHOrig = JRMCoreH.dnsH;
            GenderSlcted = JRMCoreH.dnsGender(dns);
            HairSlcted = JRMCoreH.dnsHairB(dns);
            Hair2Slcted = JRMCoreH.dnsHairF(dns);
            ColorSlcted = JRMCoreH.dnsHairC(dns);
            BreastSizeSlcted = JRMCoreH.dnsBreast(dns);
            SkinTypeSlcted = JRMCoreH.dnsSkinT(dns);
            BodyTypeSlcted = JRMCoreH.dnsBodyT(dns);
            BodyColMainSlcted = JRMCoreH.dnsBodyCM(dns);
            BodyColSub1Slcted = JRMCoreH.dnsBodyC1(dns);
            BodyColSub2Slcted = JRMCoreH.dnsBodyC2(dns);
            FaceNoseSlcted = JRMCoreH.dnsFaceN(dns);
            FaceMouthSlcted = JRMCoreH.dnsFaceM(dns);
            EyesSlcted = JRMCoreH.dnsEyes(dns);
            EyeCol1Slcted = JRMCoreH.dnsEyeC1(dns);
            EyeCol2Slcted = JRMCoreH.dnsEyeC2(dns);
        }

        csau_d();
        String s = JRMCoreH.rld("HairPresets", "jinryuujrmcore.dat");
        List<String> stooges = Arrays.asList();
        if (s.length() > 3) {
            stooges = Arrays.asList(s.split(","));
        }

        ArrayList<String> saves = new ArrayList(stooges);
        ArrayList<String> defpres = new ArrayList();
        ArrayList<String> presets = new ArrayList();

        for(String def : JRMCoreH.defHairPrsts) {
            defpres.add(def);
        }

        saves.removeAll(defpres);
        presets.addAll(defpres);
        presets.addAll(saves);
        PresetList = presets;

        for(int i = 0; i < PresetList.size(); ++i) {
            if ((PresetList.get(i)).equals(dnsH)) {
                HairPrstsSlcted = i;
                break;
            }
        }
    }

    public Object actionPerformed(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        int selct = par1 - 20;
        int KA = 0;
        GuiButton ret;
        if (KA == 1) {
            ret = new JRMCoreGuiButtons00(par1, par2, par3, par4, par5, par6Str, 0);
        } else {
            ret = new JRMCoreGuiButtons00(par1, par2, par3, par4, par5, par6Str, 0);
        }

        return ret;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        int id = button.id;

        if (this.isGUIOpen(0)) {

            if (id == 10) {
                mc.thePlayer.closeScreen();
            }

            if (id == 11) {
                this.guiID = 2;
//                JRMCoreGuiScreen DBCScreen = new JRMCoreGuiScreen(0);
//                ((IDBCGuiScreen) DBCScreen).setGuiIDPostInit(2);
//                FMLCommonHandler.instance().showGuiScreen(DBCScreen);
            }

            if (button.id == 1) {
                RaceSlctF();
                JRMCoreH.Char((byte)0, (byte)RaceSlcted);
                setchangerace();
                setdns();
            }

            if (button.id == -1) {
                RaceSlctB();
                JRMCoreH.Char((byte)0, (byte)RaceSlcted);
                setchangerace();
                setdns();
            }

            if (button.id == 2) {
                GenderSlctF();
                setdns();
            }

            if (button.id == -2) {
                GenderSlctF();
                setdns();
            }

            if (button.id == 3) {
                HairSlctF();
                setdns();
            }

            if (button.id == -3) {
                HairSlctB();
                setdns();
            }

            if (button.id >= 5100 && button.id <= 5104) {
                this.guiIDprev = this.guiID;
                dnsH = JRMCoreH.dnsH;
                int test = button.id - 5080;
                //this.guiID = button.id - 5080;
                JRMCoreGuiScreen DBCScreen = new JRMCoreGuiScreen(0);
                ((IDBCGuiScreen) DBCScreen).setGuiIDPostInit(test);
                FMLCommonHandler.instance().showGuiScreen(DBCScreen);
            }

            if (id == 4 || id == 5003 || id == 5004 || id == 5005 || id == 5009 || id == 5010 || id == 5014) {
                this.inputField2Cl = id;
                this.guiIDprev = this.guiID;
                this.guiID = 1;
                colorType = button.id;
            }

            if (button.id == 4999) {
                StateViewF();
                JRMCoreH.Char((byte)106, (byte)StateSlcted);
                setdns();
            }


            if (button.id == -4999) {
                StateViewB();
                JRMCoreH.Char((byte)106, (byte)StateSlcted);
                setdns();
            }

            if (button.id == 5001) {
                BreastSizeSlcted = (int)(((JRMCoreGuiSlider01)button).sliderValue * 10.0F);
                setdns();
            }

            if (button.id == 8) {
                YearsSlctF();
                setdns();
                JRMCoreH.Char((byte)7, (byte)YearsSlcted);
            }

            if (button.id == -8) {
                YearsSlctB();
                setdns();
                JRMCoreH.Char((byte)7, (byte)YearsSlcted);
            }

            if (button.id == 106) {
                if (tail) {
                    tail = false;
                } else {
                    tail = true;
                }

                JRMCoreH.Char((byte)103, (byte)(tail ? 1 : 0));
            }

            if (button.id == 5002) {
                SkinTypeSlcted = SlctF(SkinTypeSlcted, 2);
                setchangerace();
                setdns();
            }

            if (button.id == -5002) {
                SkinTypeSlcted = SlctB(SkinTypeSlcted, 2);
                setchangerace();
                setdns();
            }

            if (button.id == 5) {
                BodyTypeSlcted = SlctF(BodyTypeSlcted, JRMCoreH.customSknLimits[RaceSlcted][0]);
                setdns();
            }

            if (button.id == -5) {
                BodyTypeSlcted = SlctB(BodyTypeSlcted, JRMCoreH.customSknLimits[RaceSlcted][0]);
                setdns();
            }

            if (button.id == 5012) {
                BodyColPresetSlcted = SlctF(BodyColPresetSlcted, JRMCoreH.customSknLimitsBCP[RaceSlcted]);
                setchangebodycol();
                setdns();
                this.updateMajinHairToBodyColor();
            }

            if (button.id == -5012) {
                BodyColPresetSlcted = SlctB(BodyColPresetSlcted, JRMCoreH.customSknLimitsBCP[RaceSlcted]);
                setchangebodycol();
                setdns();
                this.updateMajinHairToBodyColor();
            }

            if (button.id == 5006) {
                FaceNoseSlcted = SlctF(FaceNoseSlcted, JRMCoreH.customSknLimits[RaceSlcted][2]);
                setdns();
            }

            if (button.id == -5006) {
                FaceNoseSlcted = SlctB(FaceNoseSlcted, JRMCoreH.customSknLimits[RaceSlcted][2]);
                setdns();
            }

            if (button.id == 5007) {
                FaceMouthSlcted = SlctF(FaceMouthSlcted, JRMCoreH.customSknLimits[RaceSlcted][3]);
                setdns();
            }

            if (button.id == -5007) {
                FaceMouthSlcted = SlctB(FaceMouthSlcted, JRMCoreH.customSknLimits[RaceSlcted][3]);
                setdns();
            }

            if (button.id == 5008) {
                EyesSlcted = SlctF(EyesSlcted, JRMCoreH.customSknLimits[RaceSlcted][4]);
                setdns();
            }

            if (button.id == -5008) {
                EyesSlcted = SlctB(EyesSlcted, JRMCoreH.customSknLimits[RaceSlcted][4]);
                setdns();
            }

            if (button.id == 5013) {
                EyeColPresetSlcted = SlctF(EyeColPresetSlcted, JRMCoreH.defeyecols.length);
                setchangeeyecol();
                setdns();
            }

            if (button.id == -5013) {
                EyeColPresetSlcted = SlctB(EyeColPresetSlcted, JRMCoreH.defeyecols.length);
                setchangeeyecol();
                setdns();
            }

            if (button.id == 5011) {
                EyeCol2Slcted = EyeCol1Slcted;
                setdns();
            }
        }

        if (this.isGUIOpen(1)) {

            if (button.id == -4) {
                int col = ((JRMCoreGuiButtonC)button).getBgCol();
                switch (colorType) {
                    case 4:
                        ColorSlcted = col;
                        break;
                    case 5003:
                        BodyColMainSlcted = col;
                        break;
                    case 5004:
                        BodyColSub1Slcted = col;
                        break;
                    case 5005:
                        BodyColSub2Slcted = col;
                        break;
                    case 5009:
                        EyeCol1Slcted = col;
                        break;
                    case 5010:
                        EyeCol2Slcted = col;
                        break;
                    case 5014:
                        BodyColSub3Slcted = col;
                        break;
                    case 5015:
                        KiColorSlcted = col;
                        break;
                    case 5016:
                        BodyauColMainSlcted = col;
                        setdnsau();
                        JRMCoreH.jrmcDataFC(3, dnsau);
                        break;
                    case 5017:
                        BodyauColSub1Slcted = col;
                        setdnsau();
                        JRMCoreH.jrmcDataFC(3, dnsau);
                        break;
                    case 5018:
                        BodyauColSub2Slcted = col;
                        setdnsau();
                        JRMCoreH.jrmcDataFC(3, dnsau);
                        break;
                    case 5019:
                        BodyauColSub3Slcted = col;
                        setdnsau();
                        JRMCoreH.jrmcDataFC(3, dnsau);
                }

                if (this.guiIDprev == 8) {
                    setdnsForHair();
                } else {
                    setdns();
                }

                this.updateMajinHairToBodyColor();
            }

            if (button.id == 20) {
                BrghtSlcted = ((JRMCoreGuiSlider00)button).sliderValue;
            }

            if (button.id == 33000) {
                String[] s = new String[this.inputField2.length];

                for(int idd = 0; idd < this.inputField2.length; ++idd) {
                    s[idd] = this.inputField2[idd].getText();
                }

                int[] n = new int[this.inputField2.length];

                try {
                    for(int idd = 0; idd < this.inputField2.length; ++idd) {
                        n[idd] = Integer.parseInt(s[idd]);
                        if (n[idd] < 0) {
                            n[idd] = 0;
                        }

                        if (n[idd] > 255) {
                            n[idd] = 255;
                        }
                    }

                    int col = n[0];
                    col = (col << 8) + n[1];
                    col = (col << 8) + n[2];
                    switch (colorType) {
                        case 4:
                            ColorSlcted = col;
                            break;
                        case 5003:
                            BodyColMainSlcted = col;
                            break;
                        case 5004:
                            BodyColSub1Slcted = col;
                            break;
                        case 5005:
                            BodyColSub2Slcted = col;
                            break;
                        case 5009:
                            EyeCol1Slcted = col;
                            break;
                        case 5010:
                            EyeCol2Slcted = col;
                            break;
                        case 5014:
                            BodyColSub3Slcted = col;
                            break;
                        case 5015:
                            KiColorSlcted = col;
                            break;
                        case 5016:
                            BodyauColMainSlcted = col;
                            setdnsau();
                            JRMCoreH.jrmcDataFC(3, dnsau);
                            break;
                        case 5017:
                            BodyauColSub1Slcted = col;
                            setdnsau();
                            JRMCoreH.jrmcDataFC(3, dnsau);
                            break;
                        case 5018:
                            BodyauColSub2Slcted = col;
                            setdnsau();
                            JRMCoreH.jrmcDataFC(3, dnsau);
                            break;
                        case 5019:
                            BodyauColSub3Slcted = col;
                            setdnsau();
                            JRMCoreH.jrmcDataFC(3, dnsau);
                    }

                    if (this.guiIDprev == 8) {
                        setdnsForHair();
                    } else {
                        setdns();
                    }
                } catch (Exception var23) {
                }

                this.inputField2Ch = false;
                this.updateMajinHairToBodyColor();
            }

            if (button.id == 0) {
                colorType = 0;
                this.guiID = this.guiIDprev == 8 ? this.guiIDprev : (this.guiIDprev == 19 ? this.guiIDprev : 0);
            }
        }

        if (this.isGUIOpen(2)) {

            if (button.id == 0) {
                this.guiID = this.guiIDprev == 8 ? this.guiIDprev : (this.guiIDprev == 19 ? this.guiIDprev : 0);
            }

            if (button.id == 12) {
                this.guiID = 3;
//                JRMCoreGuiScreen DBCScreen = new JRMCoreGuiScreen(0);
//                ((IDBCGuiScreen) DBCScreen).setGuiIDPostInit(3);
//                FMLCommonHandler.instance().showGuiScreen(DBCScreen);
            }

            if (button.id == 6) {
                PwrtypSlcted = Slct("F", PwrtypSlcted, JRMCoreH.PwrtypAllow);
            }

            if (button.id == -6) {
                PwrtypSlcted = Slct("B", PwrtypSlcted, JRMCoreH.PwrtypAllow);
            }

            if (button.id == 7) {
                ClassSlcted = Slct2("F", ClassSlcted, JRMCoreH.PwrtypAllow, PwrtypSlcted, cl());
            }

            if (button.id == -7) {
                ClassSlcted = Slct2("B", ClassSlcted, JRMCoreH.PwrtypAllow, PwrtypSlcted, cl());
            }

            if (id == 5015) {
                this.inputField2Cl = id;
                this.guiIDprev = this.guiID;
                this.guiID = 1;
                colorType = button.id;
            }
        }

        if (this.isGUIOpen(3)) {

            if (button.id == 11) {
                this.guiID = 2;
            }

            if (button.id == 13) {
                setdns();
                JRMCoreH.jrmcDataFC(0, dns);
                JRMCoreH.jrmcDataFC(1, dnsH);
                JRMCoreH.jrmcDataFC(2, KiColorSlcted + "");
                JRMCoreH.Char((byte)7, (byte)YearsSlcted);
                JRMCoreH.Char((byte)0, (byte)RaceSlcted);
                JRMCoreH.Char((byte)2, (byte)PwrtypSlcted);
                JRMCoreH.Char((byte)3, (byte)ClassSlcted);
                JRMCoreH.Char((byte)4, (byte)1);
                mc.thePlayer.closeScreen();
            }
        }
    }

    @Override
    public void drawScreen(int x, int y, float f) {

        int ar = 2000;
        if (this.kdf < ar) {
            ++this.kdf;
        }

        if (Mouse.isButtonDown(0)) {
            this.mousePressed = true;
            scrollSide = JRMCoreGuiSliderX00.sliderValue;
        } else {
            this.mousePressed = false;

            while(Mouse.next()) {
                int mw = Mouse.getEventDWheel();
                if (mw != 0) {
                    if (mw < 0) {
                        this.scroll += this.scrollMouseJump;
                    } else if (mw > 0 && this.scroll > 0) {
                        this.scroll -= this.scrollMouseJump;
                    }

                    this.scrollMouseJump = 1;
                }
            }
        }

        if (JRMCoreH.Accepted == 0 && this.guiID == 10) {
            this.guiID = 0;
        }

        this.x = x;
        this.y = y;
        if (this.guiID == 31 && JRMCoreH.Accepted != 2) {
            this.guiID = 30;
        }

        if (this.guiIDprev2 != 0) {
            this.guiID = this.guiIDprev2;
            this.guiIDprev2 = 0;
        }

        if (dns.length() > 1 && !dns.equals(dnsSent)) {
            dnsSent = dns;
            if (JRMCoreH.JFC() && ItemBarberSnC.barberTarget != null) {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    JRMCoreHJFC.childDNSset(ItemBarberSnC.barberTarget, dnsSent);
                }
            } else {
                JRMCoreH.jrmcDataFC(0, dnsSent);
            }
        }

        if (dnsH.length() != 786 && !dnsH.equals(dnsHSent)) {
            dnsH = JRMCoreH.dnsHairG1toG2(dnsH);
        }

        if (dnsH.length() == 786 && !dnsH.equals(dnsHSent)) {
            dnsHSent = dnsH;
            if (JRMCoreH.JFC() && ItemBarberSnC.barberTarget != null) {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    JRMCoreHJFC.childDNSHset(ItemBarberSnC.barberTarget, dnsHSent);
                }
            } else {
                JRMCoreH.jrmcDataFC(1, dnsHSent);
            }
        }

        if (dnsH.length() < 3) {
            if (JRMCoreH.JFC() && ItemBarberSnC.barberTarget != null) {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    dnsH = JRMCoreHJFC.childDNSH(ItemBarberSnC.barberTarget);
                }
            } else {
                dnsH = JRMCoreH.dnsH;
            }
        }

        if (dns.length() < 3) {
            if (JRMCoreH.JFC() && ItemBarberSnC.barberTarget != null) {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    dns = JRMCoreHJFC.childDNS(ItemBarberSnC.barberTarget);
                }
            } else {
                dns = JRMCoreH.dns;
            }
        }

        this.xSize_lo = (float)x;
        this.ySize_lo = (float)y;
        ScaledResolution var5 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        FontRenderer var8 = mc.fontRenderer;
        this.buttonList.clear();
        ++tick;
        if (tick >= 20) {
            tick = 0;
            JRMCoreH.jrmct(1);
            JRMCoreH.jrmct(3);
        }

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int posX = this.width / 2;
        int posY = this.height / 2;
        this.pwr = JRMCoreH.Pwrtyp;
        this.renderSkybox(f);
        if (this.isGUIOpen(0)) {
            int xSize = 80;
            int ySize = 107;
            int guiLeft = (this.width - xSize) / 2;
            int guiTop = (this.height - ySize) / 2;
            int buttonLeft = guiLeft - 128;
            int buttonRight = guiLeft - 24;
            int buttonText = guiLeft - 70;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation guiLocation = new ResourceLocation(wish2);
            mc.renderEngine.bindTexture(guiLocation);
            this.drawTexturedModalRect(guiLeft, guiTop, 123, 1, 80, 120);
            this.drawTexturedModalRect(guiLeft - 131, guiTop - 20, 1, 1, 120, 140);
            this.drawTexturedModalRect(guiLeft + 101, guiTop - 20, 1, 143, 160, 100);
            this.buttonList.add(new JRMCoreGuiButtons00(10, posX - 150, posY + 65, 20, 20, "X", 0));
            String n = JRMCoreH.trl("jrmc", "Next");
            int nw = this.fontRendererObj.getStringWidth(n) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(11, posX + 130, posY + 65, nw, 20, n, 0));

            String rct = t("dbcimpactgui.rcdes");
            var8.drawString(rct, guiLeft + 106, guiTop - 19, 20);
            String rdt1 = t("dbcimpactgui.rcH1");
            switch (JRMCoreH.Races[RaceSlcted]) {
                case "Saiyan":
                    rdt1 = t("dbcimpactgui.rcSaL1");
                    break;
                case "Half-Saiyan":
                    rdt1 = t("dbcimpactgui.rcHsL1");
                    break;
                case "Namekian":
                    rdt1 = t("dbcimpactgui.rcNaL1");
                    break;
                case "Arcosian":
                    rdt1 = t("dbcimpactgui.rcArL1");
                    break;
                case "Majin":
                    rdt1 = t("dbcimpactgui.rcMaL1");
                    break;
            }
            var8.drawString(rdt1, guiLeft + 106, guiTop - 5, 20);

            rdt1 = t("dbcimpactgui.rcH2");
            switch (JRMCoreH.Races[RaceSlcted]) {
                case "Saiyan":
                    rdt1 = t("dbcimpactgui.rcSaL2");
                    break;
                case "Half-Saiyan":
                    rdt1 = t("dbcimpactgui.rcHsL2");
                    break;
                case "Namekian":
                    rdt1 = t("dbcimpactgui.rcNaL2");
                    break;
                case "Arcosian":
                    rdt1 = t("dbcimpactgui.rcArL2");
                    break;
                case "Majin":
                    rdt1 = t("dbcimpactgui.rcMaL2");
                    break;
            }
            var8.drawString(rdt1, guiLeft + 106, guiTop + 5, 20);

            rdt1 = t("dbcimpactgui.rcH3");
            switch (JRMCoreH.Races[RaceSlcted]) {
                case "Saiyan":
                    rdt1 = t("dbcimpactgui.rcSaL3");
                    break;
                case "Half-Saiyan":
                    rdt1 = t("dbcimpactgui.rcHsL3");
                    break;
                case "Namekian":
                    rdt1 = t("dbcimpactgui.rcNaL3");
                    break;
                case "Arcosian":
                    rdt1 = t("dbcimpactgui.rcArL3");
                    break;
                case "Majin":
                    rdt1 = t("dbcimpactgui.rcMaL3");
                    break;
            }
            var8.drawString(rdt1, guiLeft + 106, guiTop + 15, 20);

            int race = JRMCoreH.dnsRace("dns");
            if (JRMCoreH.Allow(JRMCoreH.RaceAllow[RaceSlcted])) {
                CanRace = true;
            } else {
                CanRace = false;
                if (RaceSlcted != 0) {
                    RaceSlcted = 0;
                    JRMCoreH.Char((byte)0, (byte)RaceSlcted);
                }
            }

            if (JRMCoreH.Allow(JRMCoreH.GenderAllow[GenderSlcted]) && JRMCoreH.RaceGenders[RaceSlcted] != 1) {
                CanGender = true;
            } else {
                CanGender = false;
                if (GenderSlcted != 0) {
                    GenderSlcted = 0;
                }
            }

            if (JRMCoreH.Allow("JYC")) {
                CanYears = true;
            } else {
                CanYears = false;
                if (YearsSlcted != 0) {
                    YearsSlcted = 0;
                }
            }

            if (JRMCoreH.RaceCanHaveHair[RaceSlcted].contains("H")) {
                CanHair = true;
            } else {
                CanHair = false;
                if (HairSlcted != 10) {
                    HairSlcted = 10;
                }
            }

            if (JRMCoreH.isRaceMajin(RaceSlcted)) {
                this.updateMajinHairToBodyColor();
                CanColor = false;
                if (HairSlcted < 10) {
                    HairSlcted = 12;
                    setdnsForHair();
                }
            } else if (JRMCoreH.RaceCanHaveHair[RaceSlcted].contains("H") && JRMCoreH.RaceHairColor[RaceSlcted] == -1) {
                CanColor = true;
            } else {
                ColorSlcted = JRMCoreH.RaceHairColor[RaceSlcted] != -1 ? JRMCoreH.RaceHairColor[RaceSlcted] : 0;
                CanColor = false;
            }

            if (JRMCoreH.RaceCustomSkin[RaceSlcted] == 2) {
                canCustom = true;
            } else if (JRMCoreH.RaceCustomSkin[RaceSlcted] == 1) {
                canCustom = false;
                if (SkinTypeSlcted != 1) {
                    SkinTypeSlcted = 1;
                    setdns();
                }
            } else {
                canCustom = false;
                if (SkinTypeSlcted != 0) {
                    SkinTypeSlcted = 0;
                    setdns();
                }
            }

            String Race = JRMCoreH.trl("jrmc", JRMCoreH.Races[RaceSlcted]);
            String Gender = JRMCoreH.trl("jrmc", JRMCoreH.Genders[GenderSlcted]);
            String Years = JRMCoreH.trl("jrmc", JRMCoreH.Years[YearsSlcted]);
            String TRState = JRMCoreH.trl("jrmc", "TRState");
            String Hair = JRMCoreH.trl("jrmc", "Hair") + " " + (HairSlcted + 1);
            String Color = "" + ColorSlcted;
            String SkinTyp = JRMCoreH.trl("jrmc", JRMCoreH.skinTyps[SkinTypeSlcted]);
            String Tail = JRMCoreH.trl("jrmc", "Tail");
            int i = 0;
            if (CanRace) {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-1, guiLeft - 21, guiTop + 5 + 120, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(1, guiLeft + 91, guiTop + 5 + 120, ">"));
            }

            var8.drawString(Race, guiLeft + 41 - var8.getStringWidth(Race) / 2, guiTop + 5 + 120, 0);

            if (CanGender) {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-2, guiLeft -11, guiTop + 5 + 130, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(2, guiLeft + 81, guiTop + 5 + 130, ">"));
                var8.drawString(Gender, guiLeft + 41 - var8.getStringWidth(Gender) / 2, guiTop + 5 + 130, 0);
            }


            if (CanHair) {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-3, buttonLeft, guiTop - 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(3, buttonRight, guiTop - 5 + i * 10, ">"));
                if (HairSlcted != 12) {
                    var8.drawString(Hair, buttonText - var8.getStringWidth(Hair) / 2, guiTop - 5 + i * 10, 0);
                }

                String s = JRMCoreH.trl("jrmc", "CustomHair");
                int sw = this.fontRendererObj.getStringWidth(s) / 2;
                if (HairSlcted == 12) {
                    this.buttonList.add((new JRMCoreGuiButtons01(5100, buttonText - sw, guiTop - 5 + i * 10, sw, s, JRMCoreH.techNCCol[1])).setShadow(false));
                }
            }

            ++i;
            if (CanColor) {
                if (RaceSlcted != 4 && RaceSlcted != 3) {
                    float h2 = (float)(ColorSlcted >> 16 & 255) / 255.0F;
                    float h3 = (float)(ColorSlcted >> 8 & 255) / 255.0F;
                    float h4 = (float)(ColorSlcted & 255) / 255.0F;
                    float h1 = 1.0F;
                    GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, 1.0F);
                    mc.renderEngine.bindTexture(new ResourceLocation(button1));
                    this.drawTexturedModalRect(buttonText - 25, guiTop -6 + i * 10, 0, 0, 50, 10);
                } else if (RaceSlcted == 4) {
                    String TransNms = TRState + ": " + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", JRMCoreH.TransNms[RaceSlcted][JRMCoreH.State]);
                    this.buttonList.add(new JRMCoreGuiButtonsA2(-4999, buttonLeft, guiTop - 5 + i * 10 - 1, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(4999, buttonRight, guiTop - 5 + i * 10 - 1, ">"));
                    var8.drawString(TransNms, buttonText - var8.getStringWidth(TransNms) / 2, guiTop - 5 + i * 10, 0);
                }

                String s = JRMCoreH.trl("jrmc", "Color");
                int sw = this.fontRendererObj.getStringWidth(s) / 2;
                this.buttonList.add((new JRMCoreGuiButtons01(4, buttonText - sw, guiTop - 5 + i * 10, sw, s, JRMCoreH.techNCCol[1])).setShadow(false));
            }

            ++i;
            if (GenderSlcted == 1) {
                this.buttonList.add(new JRMCoreGuiSlider01(5001, guiLeft + 40 - 25, guiTop + 5 + 140, 50, 10, "", (float)BreastSizeSlcted * 0.1F, 1.0F));
            }

            if (CanYears) {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-8, buttonLeft, guiTop - 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(8, buttonRight, guiTop - 5 + i * 10, ">"));
                var8.drawString(Years, buttonText - var8.getStringWidth(Years) / 2, guiTop - 5 + i * 10, 0);
            }

            ++i;
            if (RaceSlcted == 2) {
                String s = Tail + " " + (tail ? JRMCoreH.trl("jrmc", "Enabled") : JRMCoreH.trl("jrmc", "Disabled"));
                int sw = this.fontRendererObj.getStringWidth(s) / 2;
                this.buttonList.add((new JRMCoreGuiButtons01(106, buttonText - sw, guiTop - 5 + i * 10, sw, s, tail ? 3452672 : 4210752)).setShadow(false));
            }

            ++i;
            if (canCustom) {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-5002, buttonLeft, guiTop - 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(5002, buttonRight, guiTop - 5 + i * 10, ">"));
            }

            var8.drawString(SkinTyp, buttonText - var8.getStringWidth(SkinTyp) / 2, guiTop - 5 + i * 10, 0);
            ++i;
            boolean fc = false;
            if (SkinTypeSlcted == 1) {
                if (SkinTypeSlcted == 1) {
                    String Special = JRMCoreH.trl("jrmc", "BodyType") + " " + (BodyTypeSlcted + 1);
                    var8.drawString(Special, buttonText - var8.getStringWidth(Special) / 2, guiTop - 5 + i * 10, 0);
                    this.buttonList.add(new JRMCoreGuiButtonsA2(-5, buttonLeft, guiTop - 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5, buttonRight, guiTop - 5 + i * 10, ">"));
                    ++i;
                    this.buttonList.add(new JRMCoreGuiButtonsA2(-5012, buttonLeft, guiTop - 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5012, buttonRight, guiTop - 5 + i * 10, ">"));
                    int cls = JRMCoreH.customSknLimits[RaceSlcted][1];
                    if (cls >= 1) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5003, buttonText - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0)), guiTop - 5 - 1 + i * 10, 20, 10, "", BodyColMainSlcted));
                    }

                    if (cls >= 2) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5004, buttonText - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 21, guiTop - 5 - 1 + i * 10, 20, 10, "", BodyColSub1Slcted));
                    }

                    if (cls >= 3) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5005, buttonText - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 42, guiTop - 5 - 1 + i * 10, 20, 10, "", BodyColSub2Slcted));
                    }

                    if (cls >= 4) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5014, buttonText - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 63, guiTop - 5 - 1 + i * 10, 20, 10, "", BodyColSub3Slcted));
                    }

                    ++i;
                    int fcs = i;
                    this.buttonList.add(new JRMCoreGuiButtonsA2(-5006, buttonLeft, guiTop - 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5006, buttonRight, guiTop - 5 + i * 10, ">"));
                    Special = JRMCoreH.trl("jrmc", "Nose") + " " + (FaceNoseSlcted + 1);
                    var8.drawString(Special, buttonText - var8.getStringWidth(Special) / 2, guiTop - 5 + i * 10, 0);
                    ++i;
                    Special = JRMCoreH.trl("jrmc", "Mouth") + " " + (FaceMouthSlcted + 1);
                    var8.drawString(Special, buttonText - var8.getStringWidth(Special) / 2, guiTop - 5 + i * 10, 0);
                    this.buttonList.add(new JRMCoreGuiButtonsA2(-5007, buttonLeft, guiTop - 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5007, buttonRight, guiTop - 5 + i * 10, ">"));
                    ++i;
                    Special = JRMCoreH.trl("jrmc", "Eyes") + " " + (EyesSlcted + 1);
                    var8.drawString(Special, buttonText - var8.getStringWidth(Special) / 2, guiTop - 5 + i * 10, 0);
                    this.buttonList.add(new JRMCoreGuiButtonsA2(-5008, buttonLeft, guiTop - 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5008, buttonRight, guiTop - 5 + i * 10, ">"));
                    ++i;
                    cls = JRMCoreH.customSknLimits[RaceSlcted][5];
                    if (cls != 0) {
                        this.buttonList.add(new JRMCoreGuiButtonsA2(-5013, buttonLeft, guiTop - 5 + i * 10, "<"));
                        this.buttonList.add(new JRMCoreGuiButtonsA2(5013, buttonRight, guiTop - 5 + i * 10, ">"));
                    }

                    if (cls >= 1) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5009, buttonText - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0)), guiTop - 5 - 1 + i * 10, 20, 10, "", EyeCol1Slcted));
                    }

                    if (cls >= 2) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5010, buttonText - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 21, guiTop - 5 - 1 + i * 10, 20, 10, "", EyeCol2Slcted));
                        ++i;
                        Special = JRMCoreH.trl("jrmc", "Match");
                        int sw = this.fontRendererObj.getStringWidth(Special) / 2;
                        this.buttonList.add((new JRMCoreGuiButtons01(5011, buttonText - sw, guiTop - 5 + i * 10, sw, Special, JRMCoreH.techNCCol[1])).setShadow(false));
                    }

                    ++i;
                    fc = hovered(x, y, buttonLeft, guiTop - 5 + fcs * 10 + 2, 120, 7 + i * 3);
                }
            } else {
                int cls = JRMCoreH.customSknLimits[RaceSlcted][1];
                if (cls >= 2) {
                    this.buttonList.add(new JRMCoreGuiButtonC1(5004, buttonText - 10 + (cls - 0) * -10 - (cls > 1 ? cls - 1 : 0) + 21, guiTop - 5 - 1 + i * 10, 20, 10, "", BodyColSub1Slcted));
                }
            }

            if (RaceSlcted == 1 && !tail) {
                tail = true;
                JRMCoreH.Char((byte)103, (byte)(tail ? 1 : 0));
            }

            JRMCoreClient.mc.mouseHelper.mouseXYChange();
            float posXm = (float)Mouse.getX() * 1.0F / ((float)JRMCoreClient.mc.displayWidth * 1.0F);
            float posYm = (float)Mouse.getY() * 1.0F / ((float)JRMCoreClient.mc.displayHeight * 1.0F);
            int var1387 = (int)((float)var6 * posXm);
            var1387 = var7 - (int)((float)var7 * posYm);
            if (fc) {
                func_110423_a(guiLeft + 71, guiTop + 155 + 190, 180, (float)(guiLeft + 51) - this.xSize_lo, (float)(guiTop + 80) - this.ySize_lo, mc.thePlayer, false, false, false);
            } else {
                func_110423_a_I(guiLeft + 40, guiTop + 118, 60, (float)(guiLeft + 40) - this.xSize_lo, (float)(guiTop + 50) - this.ySize_lo, mc.thePlayer);
            }

            String s = JRMCoreH.trl("jrmc", "Appearance");
            int sw = this.fontRendererObj.getStringWidth(s) / 2;
            var8.drawString(s, buttonText - sw, guiTop - 15, 0);
        }

        ufc = false;
        if (this.isGUIOpen(1)) {
            int xSize = 256;
            int ySize = 159;
            int guiLeft = (this.width - xSize) / 2;
            int guiTop = (this.height - ySize) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation guiLocation = new ResourceLocation(wish);
            mc.renderEngine.bindTexture(guiLocation);
            this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
            int a = 0;

            for(int X = 0; X < 128; ++X) {
                for(int Y = 0; Y < 128; ++Y) {
                    Color i = Color.getHSBColor((float)X * 2.0F / 255.0F, (float)Y * 2.0F / 255.0F, BrghtSlcted);
                    int cc = i.getRed() * 65536 + i.getGreen() * 256 + i.getBlue();
                    if (colorType == 5015 && cc == 0) {
                        a = 11075583;
                        if (JRMCoreH.align <= 66 && JRMCoreH.align >= 33) {
                            a = 14526719;
                        }

                        if (JRMCoreH.align < 33) {
                            a = 16646144;
                        }

                        this.buttonList.add(new JRMCoreGuiButtonC(-4, guiLeft + 5 + X, guiTop + 5 + Y, 1, 1, "", cc, a));
                    } else {
                        this.buttonList.add(new JRMCoreGuiButtonC(-4, guiLeft + 5 + X, guiTop + 5 + Y, 1, 1, "", cc));
                    }
                }
            }

            if (colorType == 5015 && BrghtSlcted == 0.0F) {
                var8.drawString(JRMCoreH.trl("jrmc", "AlignmentBased"), guiLeft + 5, guiTop + 140, 0);
            }

            this.buttonList.add(new JRMCoreGuiSlider00(20, guiLeft + 135, guiTop + 5, "", BrghtSlcted, 1.0F));
            int clr = 0;
            if (this.inputField2Cl == 4) {
                clr = ColorSlcted;
            } else if (this.inputField2Cl == 5015) {
                clr = KiColorSlcted;
            } else if (this.inputField2Cl == 5003) {
                clr = BodyColMainSlcted;
            } else if (this.inputField2Cl == 5004) {
                clr = BodyColSub1Slcted;
            } else if (this.inputField2Cl == 5005) {
                clr = BodyColSub2Slcted;
            } else if (this.inputField2Cl == 5014) {
                clr = BodyColSub3Slcted;
            } else if (this.inputField2Cl == 5009) {
                clr = EyeCol1Slcted;
            } else if (this.inputField2Cl == 5010) {
                clr = EyeCol2Slcted;
            } else if (this.inputField2Cl == 5016) {
                clr = BodyauColMainSlcted;
            } else if (this.inputField2Cl == 5017) {
                clr = BodyauColSub1Slcted;
            } else if (this.inputField2Cl == 5018) {
                clr = BodyauColSub2Slcted;
            } else if (this.inputField2Cl == 5019) {
                clr = BodyauColSub3Slcted;
            }

            float h2 = (float)(clr >> 16 & 255) / 255.0F;
            float h3 = (float)(clr >> 8 & 255) / 255.0F;
            float h4 = (float)(clr & 255) / 255.0F;
            float h1 = 1.0F;
            GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, 1.0F);
            mc.renderEngine.bindTexture(new ResourceLocation(button1));
            this.drawTexturedModalRect(guiLeft + 180, guiTop + 65, 0, 0, 50, 10);
            String[] rgb = new String[]{"Red", "Green", "Blue"};

            for(int id = 0; id < this.inputField2.length; ++id) {
                String s = rgb[id] + ":";
                int sw = this.fontRendererObj.getStringWidth(s) / 2;
                var8.drawString(s, guiLeft + 158, guiTop + 15 + id * 15, 0);
                this.nametf(var8, id, 0, 0);
                if (this.inputField2[id] != null) {
                    this.inputField2[id].xPosition = guiLeft + 195;
                    this.inputField2[id].yPosition = guiTop + 15 + id * 15;
                    this.inputField2[id].drawTextBox();
                }
            }

            String s = "Get RGB Color";
            int sw = this.fontRendererObj.getStringWidth(s) / 2;
            this.buttonList.add((new JRMCoreGuiButtons01(33000, guiLeft + 205 - sw, guiTop + 80, sw, s, JRMCoreH.techNCCol[1])).setShadow(false));
            s = JRMCoreH.trl("jrmc", "Back");
            sw = this.fontRendererObj.getStringWidth(s) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(0, posX - 130 - sw, posY + 65, sw, 20, s, 0));
        }

        if (this.isGUIOpen(2)) {
            int xSize = 256;
            int ySize = 159;
            int guiLeft = (this.width - xSize) / 2;
            int guiTop = (this.height - ySize) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation guiLocation = new ResourceLocation(wish);
            mc.renderEngine.bindTexture(guiLocation);
            this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
            String n = JRMCoreH.trl("jrmc", "Back");
            int nw = this.fontRendererObj.getStringWidth(n) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(0, posX - 130 - nw, posY + 65, nw, 20, n, 0));
            n = JRMCoreH.trl("jrmc", "Next");
            nw = this.fontRendererObj.getStringWidth(n) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(12, posX + 130, posY + 65, nw, 20, n, 0));
            if (JRMCoreH.Allow(JRMCoreH.PwrtypAllow[PwrtypSlcted])) {
                CanPwr = true;
            } else {
                PwrtypSlcted = 0;
                CanPwr = false;
            }

            if (JRMCoreH.Allow(JRMCoreH.PwrtypAllow[PwrtypSlcted]) && PwrtypSlcted != 3) {
                CanClass = true;
            } else {
                ClassSlcted = 0;
                CanClass = false;
            }

            if (cl().length - 1 < ClassSlcted) {
                ClassSlcted = 0;
            }

            if (!JRMCoreH.RaceCanHavePwr[RaceSlcted].contains("" + PwrtypSlcted)) {
                PwrtypSlcted = Slct("B", PwrtypSlcted, JRMCoreH.PwrtypAllow);
            }

            String Pwrtyp = JRMCoreH.trl("jrmc", JRMCoreH.Pwrtyps[PwrtypSlcted]);
            String Class = JRMCoreH.trl("jrmc", cl()[ClassSlcted]);
            String ClassDesc = JRMCoreH.trl("jrmc", clDesc()[ClassSlcted]);
            String Stats = JRMCoreH.trl("jrmc", "Stats");
            String StatIncreaseDesc = JRMCoreH.trl("jrmc", "StatIncreaseDesc");
            String StartAttr = JRMCoreH.trl("jrmc", "StartAttr");
            var8.drawString(JRMCoreH.trl("jrmc", "PowerType"), guiLeft + 5, guiTop + 5, 0);
            int i = 1;
            if (CanPwr) {
                boolean yes = false;
                if (!JRMCoreH.DBC() && !JRMCoreH.NC()) {
                    if (JRMCoreH.SAOC()) {
                        yes = true;
                    }
                } else {
                    yes = true;
                }

                if (yes) {
                    this.buttonList.add(new JRMCoreGuiButtonsA2(-6, guiLeft + 130 - 125, guiTop + 4 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(6, guiLeft + 240 - 125, guiTop + 4 + i * 10, ">"));
                }

                String desc = JRMCoreH.trl(PwrtypSlcted == 3 ? "saoc" : "jrmc", JRMCoreH.PwrtypDesc[PwrtypSlcted]);
                int wpos = var8.getStringWidth(Pwrtyp);
                int xpos = guiLeft + 64 - wpos / 2;
                int ypos = guiTop + 5 + i * 10;
                drawDetails(Pwrtyp, desc, xpos, ypos, x, y, var8);
            }

            ++i;
            if (CanClass) {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-7, guiLeft + 130 - 125, guiTop + 4 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(7, guiLeft + 240 - 125, guiTop + 4 + i * 10, ">"));
                int wpos = var8.getStringWidth(Class);
                int xpos = guiLeft + 64 - wpos / 2;
                int ypos = guiTop + 5 + i * 10;
                drawDetails(Class, ClassDesc, xpos, ypos, x, y, var8);
            }

            ++i;
            if (PwrtypSlcted == 1) {
                int col = KiColorSlcted > 0 ? KiColorSlcted : 11075583;
                float h2 = (float)(col >> 16 & 255) / 255.0F;
                float h3 = (float)(col >> 8 & 255) / 255.0F;
                float h4 = (float)(col & 255) / 255.0F;
                float h1 = 1.0F;
                GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, 1.0F);
                mc.renderEngine.bindTexture(new ResourceLocation(button1));
                this.drawTexturedModalRect(guiLeft + 64 - 50 + 1, guiTop + 4 + i * 10, 0, 0, 100, 10);
                String s = JRMCoreH.trl("jrmc", "AuraColor");
                int sw = this.fontRendererObj.getStringWidth(s) / 2;
                this.buttonList.add((new JRMCoreGuiButtons01(5015, guiLeft + 64 - sw, guiTop + 5 + i * 10, sw, s, JRMCoreH.techNCCol[1])).setShadow(false));
            }

            ++i;
            var8.drawString(Stats, guiLeft + 5, guiTop + 5 + i * 10, 0);
            var8.drawString(StartAttr, guiLeft + 5 + 128, guiTop + 5 + i * 10, 0);
            ++i;
            int STR = JRMCoreH.attributeStart(PwrtypSlcted, 0, RaceSlcted, ClassSlcted);
            int DEX = JRMCoreH.attributeStart(PwrtypSlcted, 1, RaceSlcted, ClassSlcted);
            int CON = JRMCoreH.attributeStart(PwrtypSlcted, 2, RaceSlcted, ClassSlcted);
            int WIL = JRMCoreH.attributeStart(PwrtypSlcted, 3, RaceSlcted, ClassSlcted);
            int MND = JRMCoreH.attributeStart(PwrtypSlcted, 4, RaceSlcted, ClassSlcted);
            int SPI = JRMCoreH.attributeStart(PwrtypSlcted, 5, RaceSlcted, ClassSlcted);
            if (PwrtypSlcted != 1 && PwrtypSlcted != 2) {
                if (PwrtypSlcted == 3) {
                    int stat = JRMCoreH.stat(mc.thePlayer, 0, PwrtypSlcted, 0, STR, RaceSlcted, ClassSlcted, 0.0F);
                    int inc = (int)JRMCoreH.statInc[PwrtypSlcted][0];
                    String statNam = JRMCoreH.trl("saoc", JRMCoreH.attrStat[PwrtypSlcted][0]);
                    String Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                    String statAttr = "STR";
                    drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                    String startAttr = JRMCoreH.cldgy + "STR: " + JRMCoreH.cldr + STR;
                    String AttrDesc = JRMCoreH.trl("saoc", JRMCoreH.attrDsc[PwrtypSlcted][0]);
                    drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                    ++i;
                    stat = JRMCoreH.stat(mc.thePlayer, 2, PwrtypSlcted, 2, CON, RaceSlcted, ClassSlcted, 0.0F);
                    inc = (int)JRMCoreH.statInc[PwrtypSlcted][2];
                    statNam = JRMCoreH.trl("saoc", JRMCoreH.attrStat[PwrtypSlcted][2]);
                    Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                    statAttr = "VIT";
                    drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                    startAttr = JRMCoreH.cldgy + "AGI: " + JRMCoreH.cldr + DEX;
                    AttrDesc = JRMCoreH.trl("saoc", JRMCoreH.attrDsc[PwrtypSlcted][1]);
                    drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                    ++i;
                    startAttr = JRMCoreH.cldgy + "VIT: " + JRMCoreH.cldr + CON;
                    AttrDesc = JRMCoreH.trl("saoc", JRMCoreH.attrDsc[PwrtypSlcted][2]);
                    drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                    ++i;
                } else {
                    int stat = JRMCoreH.stat(mc.thePlayer, 0, PwrtypSlcted, 0, STR, RaceSlcted, ClassSlcted, 0.0F);
                    float inc = JRMCoreH.statInc[PwrtypSlcted][0];
                    String statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][0]);
                    String Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                    String statAttr = "STR";
                    drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                    String startAttr = JRMCoreH.cldgy + "STR: " + JRMCoreH.cldr + STR;
                    String AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][0]);
                    drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                    ++i;
                    stat = JRMCoreH.stat(mc.thePlayer, 2, PwrtypSlcted, 2, CON, RaceSlcted, ClassSlcted, 0.0F);
                    inc = JRMCoreH.statInc[PwrtypSlcted][2];
                    statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][2]);
                    Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                    statAttr = "CON";
                    drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                    startAttr = JRMCoreH.cldgy + "CON: " + JRMCoreH.cldr + CON;
                    AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][2]);
                    drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                    ++i;
                }
            } else {
                int stat = JRMCoreH.stat(mc.thePlayer, 0, PwrtypSlcted, 0, STR, RaceSlcted, ClassSlcted, 0.0F);
                float inc = JRMCoreH.statInc(PwrtypSlcted, 0, 1, RaceSlcted, ClassSlcted, 0.0F);
                String statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][0]);
                String Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                String statAttr = "STR";
                drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                String startAttr = JRMCoreH.cldgy + "STR: " + JRMCoreH.cldr + STR;
                String AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][0]);
                drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                ++i;
                stat = JRMCoreH.stat(mc.thePlayer, 1, PwrtypSlcted, 1, DEX, RaceSlcted, ClassSlcted, 0.0F);
                inc = JRMCoreH.statInc(PwrtypSlcted, 1, 1, RaceSlcted, ClassSlcted, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][1]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = "DEX";
                drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                startAttr = JRMCoreH.cldgy + "DEX: " + JRMCoreH.cldr + DEX;
                AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][1]);
                drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                ++i;
                stat = JRMCoreH.stat(mc.thePlayer, 2, PwrtypSlcted, 2, CON, RaceSlcted, ClassSlcted, 0.0F);
                inc = JRMCoreH.statInc(PwrtypSlcted, 2, 1, RaceSlcted, ClassSlcted, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][2]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = "CON";
                drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                startAttr = JRMCoreH.cldgy + "CON: " + JRMCoreH.cldr + CON;
                AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][2]);
                drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                ++i;
                stat = JRMCoreH.stat(mc.thePlayer, 2, PwrtypSlcted, 3, CON, RaceSlcted, ClassSlcted, 0.0F);
                inc = JRMCoreH.statInc(PwrtypSlcted, 3, 1, RaceSlcted, ClassSlcted, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][3]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = "CON";
                drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                startAttr = JRMCoreH.cldgy + "WIL: " + JRMCoreH.cldr + WIL;
                AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][3]);
                drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                ++i;
                stat = JRMCoreH.stat(mc.thePlayer, 3, PwrtypSlcted, 4, WIL, RaceSlcted, ClassSlcted, 0.0F);
                inc = JRMCoreH.statInc(PwrtypSlcted, 4, 1, RaceSlcted, ClassSlcted, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][4]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = "WIL";
                drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                startAttr = JRMCoreH.cldgy + "MND: " + JRMCoreH.cldr + MND;
                AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][4]);
                drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                ++i;
                stat = JRMCoreH.stat(mc.thePlayer, 5, PwrtypSlcted, 5, SPI, RaceSlcted, ClassSlcted, JRMCoreH.SklLvl_KiBs(this.pwr));
                inc = JRMCoreH.statInc(PwrtypSlcted, 5, 1, RaceSlcted, ClassSlcted, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[PwrtypSlcted][5]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = "SPI";
                drawDetails(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[]{statAttr, inc}), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);
                startAttr = JRMCoreH.cldgy + "SPI: " + JRMCoreH.cldr + SPI;
                AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[PwrtypSlcted][5]);
                drawDetails(startAttr, AttrDesc, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                ++i;
            }
        }

        if (this.isGUIOpen(3)) {
            int xSize = 256;
            int ySize = 159;
            int guiLeft = (this.width - xSize) / 2;
            int guiTop = (this.height - ySize) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation guiLocation = new ResourceLocation(wish);
            mc.renderEngine.bindTexture(guiLocation);
            this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
            String n = JRMCoreH.trl("jrmc", "Back");
            int nw = this.fontRendererObj.getStringWidth(n) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(11, posX - 130 - nw, posY + 65, nw, 20, n, 0));
            n = JRMCoreH.trl("jrmc", "Accept");
            nw = this.fontRendererObj.getStringWidth(n) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(13, posX + 130, posY + 65, nw, 20, n, 0));
            String Race = JRMCoreH.trl("jrmc", JRMCoreH.Races[RaceSlcted]);
            String Gender = JRMCoreH.trl("jrmc", JRMCoreH.Genders[GenderSlcted]);
            String Hair = JRMCoreH.trl("jrmc", "Hair") + " " + (HairSlcted + 1);
            String Color = "" + ColorSlcted;
            String Special = JRMCoreH.trl("jrmc", "BodyType") + " " + (BodyTypeSlcted + 1);
            String Pwrtyp = JRMCoreH.trl("jrmc", JRMCoreH.Pwrtyps[PwrtypSlcted]);
            String Class = JRMCoreH.trl("jrmc", cl()[ClassSlcted]);
            String className = JRMCoreH.trl("jrmc", JRMCoreH.ClassNames[PwrtypSlcted]);
            String SkinTyp = JRMCoreH.trl("jrmc", JRMCoreH.skinTyps[SkinTypeSlcted]);
            int i = 0;
            var8.drawString(JRMCoreH.trl("jrmc", "Race") + ": " + Race, guiLeft + 5, guiTop + 5 + i * 10, 0);
            ++i;
            var8.drawString(JRMCoreH.trl("jrmc", "Gender") + ": " + Gender, guiLeft + 5, guiTop + 5 + i * 10, 0);
            ++i;
            var8.drawString(JRMCoreH.trl("jrmc", "Hair") + ": " + Hair, guiLeft + 5, guiTop + 5 + i * 10, 0);
            ++i;
            var8.drawString(JRMCoreH.trl("jrmc", "Color") + ": ", guiLeft + 5, guiTop + 5 + i * 10, 0);
            ++i;
            var8.drawString(JRMCoreH.trl("jrmc", "BodyType") + ": " + SkinTyp, guiLeft + 5, guiTop + 5 + i * 10, 0);
            ++i;
            var8.drawString(JRMCoreH.trl("jrmc", "PowerType") + ": " + Pwrtyp, guiLeft + 5, guiTop + 5 + i * 10, 0);
            ++i;
            if (PwrtypSlcted != 3) {
                var8.drawString(className + ": " + Class, guiLeft + 5, guiTop + 5 + i * 10, 0);
            }

            ++i;
            float h2 = (float)(ColorSlcted >> 16 & 255) / 255.0F;
            float h3 = (float)(ColorSlcted >> 8 & 255) / 255.0F;
            float h4 = (float)(ColorSlcted & 255) / 255.0F;
            float h1 = 1.0F;
            GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, 1.0F);
            mc.renderEngine.bindTexture(new ResourceLocation(button1));
            this.drawTexturedModalRect(guiLeft + 5 + var8.getStringWidth("Color: "), guiTop + 3 + 30, 0, 0, 50, 10);
            int k = guiLeft + xSize / 2;
            func_110423_a_I(k + 51, guiTop + 155, 60, (float)(k + 51) - this.xSize_lo, (float)(guiTop + 120 - 40) - this.ySize_lo, mc.thePlayer);
        }

        super.drawScreen(x, y, f);
        this.drawDetails(x, y, var8);
    }

    @Override
    public void updateScreen() {
        ++this.panoramaTimer;
        if (this.inputField != null) {
            this.inputField.updateCursorCounter();
        }

        for(int id = 0; id < this.inputField2.length; ++id) {
            if (this.inputField2 != null && this.inputField2[id] != null) {
                this.inputField2[id].updateCursorCounter();
            }
        }

    }

    @Override
    protected void keyTyped(char c, int i) {
        super.keyTyped(c, i);
        if (this.inputField != null) {
            this.inputField.textboxKeyTyped(c, i);
        }

        for(int id = 0; id < this.inputField2.length; ++id) {
            if (this.inputField2 != null && this.inputField2[id] != null) {
                this.inputField2Ch = true;
                this.inputField2[id].textboxKeyTyped(c, i);
            }
        }

    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        if (this.inputField != null) {
            this.inputField.mouseClicked(i, j, k);
        }

        for(int id = 0; id < this.inputField2.length; ++id) {
            if (this.inputField2 != null && this.inputField2[id] != null) {
                this.inputField2[id].mouseClicked(i, j, k);
            }
        }

    }

    private void drawDetails(int x, int y, FontRenderer var8) {
        if (!detailList.isEmpty()) {
            Object[] o = (Object[]) detailList.get(0);
            String desc = (String)o[0];
            int ll = Integer.parseInt("" + o[6]);
            int descw = var8.getStringWidth(desc);
            int var10000 = 1 + var8.getStringWidth(desc) / ll;
            mc.renderEngine.bindTexture(new ResourceLocation("jinryuumodscore:allw.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
            int desch = JRMCoreH.txt(desc, (String)o[1], Integer.parseInt("" + o[2]), false, 0, 0, ll);
            ScaledResolution var5 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            int var6 = var5.getScaledWidth();
            int var7 = var5.getScaledHeight();
            int xp = 0;
            int yp = 0;
            if (var6 < x + (descw < ll ? descw : ll) + 10) {
                xp += var6 - (x + (descw < ll ? descw : ll)) - 10;
            }

            if (var7 < y + desch * 10 + 10) {
                yp = -(desch * 10 + 20);
            }

            this.drawTexturedModalRect(x + xp, y + 10 + yp, 5, 5, (descw < ll ? descw : ll) + 10, desch * 10 + 10);
            JRMCoreH.txt(desc, (String)o[1], Integer.parseInt("" + o[2]), Boolean.parseBoolean("" + o[3]), Integer.parseInt("" + o[4]) + xp, Integer.parseInt("" + o[5]) + 10 + yp, ll);
            detailList.clear();
        }

    }

    private static void drawDetails(String s1, String s2, int xpos, int ypos, int x, int y, FontRenderer var8) {
        int wpos = var8.getStringWidth(s1);
        var8.drawString(s1, xpos, ypos, 0);
        if (xpos < x && xpos + wpos > x && ypos - 3 < y && ypos + 10 > y) {
            int ll = 200;
            Object[] txt = new Object[]{s2, "§8", 0, true, x + 5, y + 5, ll};
            detailList.add(txt);
        }

    }

    public static void setchangebodycol() {
        int[][] pres = JRMCoreH.defbodycols[BodyColPresetSlcted];
        BodyColMainSlcted = pres[RaceSlcted].length > 0 ? pres[RaceSlcted][0] : 0;
        BodyColSub1Slcted = pres[RaceSlcted].length > 1 ? pres[RaceSlcted][1] : 0;
        BodyColSub2Slcted = pres[RaceSlcted].length > 2 ? pres[RaceSlcted][2] : 0;
        BodyColSub3Slcted = pres[RaceSlcted].length > 3 ? pres[RaceSlcted][3] : 0;
    }

    public static void setchangeeyecol() {
        int[] preseyes = JRMCoreH.defeyecols[EyeColPresetSlcted];
        EyeCol1Slcted = preseyes[RaceSlcted];
        EyeCol2Slcted = preseyes[RaceSlcted];
    }

    public static void setchangerace() {
        GenderSlcted = JRMCoreH.RaceGenders[RaceSlcted] == 1 ? 0 : GenderSlcted;
        if (RaceSlcted == 4) {
            StateSlcted = 4;
        } else {
            StateSlcted = 0;
        }

        int bt = JRMCoreH.customSknLimits[RaceSlcted][0];
        BodyTypeSlcted = BodyTypeSlcted > bt - 1 ? bt - 1 : BodyTypeSlcted;
        setchangebodycol();
        bt = JRMCoreH.customSknLimits[RaceSlcted][2];
        FaceNoseSlcted = FaceNoseSlcted > bt - 1 ? bt - 1 : FaceNoseSlcted;
        bt = JRMCoreH.customSknLimits[RaceSlcted][3];
        FaceMouthSlcted = FaceMouthSlcted > bt - 1 ? bt - 1 : FaceMouthSlcted;
        bt = JRMCoreH.customSknLimits[RaceSlcted][4];
        EyesSlcted = EyesSlcted > bt - 1 ? bt - 1 : EyesSlcted;
        setchangeeyecol();
        setdns();
    }

    public static void csau_d() {
        String dnsau = JRMCoreH.data(JRMCoreClient.mc.thePlayer.getCommandSenderName(), 16, "");
        dnsau = dnsau.contains(";") ? dnsau.substring(1) : dnsau;
        BodyauColMainSlcted = JRMCoreH.dnsauCM(dnsau);
        BodyauColSub1Slcted = JRMCoreH.dnsauC1(dnsau);
        BodyauColSub2Slcted = JRMCoreH.dnsauC2(dnsau);
        BodyauColSub3Slcted = JRMCoreH.dnsauC3(dnsau);
    }

    public static void csau_df() {
        BodyauColMainSlcted = 14208118;
        BodyauColSub1Slcted = 10317733;
        BodyauColSub2Slcted = 6966676;
        BodyauColSub3Slcted = 11045420;
    }

    public static void setdnsau() {
        String BCM = ntl5(BodyauColMainSlcted);
        String BC1 = ntl5(BodyauColSub1Slcted);
        String BC2 = ntl5(BodyauColSub2Slcted);
        String BC3 = ntl5(BodyauColSub3Slcted);
        dnsau = BCM + BC1 + BC2 + BC3;
    }

    public static void setdns() {
        GenderSlcted = GenderSlcted > 9 ? 9 : (GenderSlcted < 0 ? 0 : GenderSlcted);
        BreastSizeSlcted = BreastSizeSlcted > 9 ? 9 : (BreastSizeSlcted < 0 ? 0 : BreastSizeSlcted);
        SkinTypeSlcted = SkinTypeSlcted > 9 ? 9 : (SkinTypeSlcted < 0 ? 0 : SkinTypeSlcted);
        String R = ntl(RaceSlcted);
        String G = GenderSlcted + "";
        String H1 = ntl(HairSlcted);
        String H2 = ntl(Hair2Slcted);
        String HC = ntl5(ColorSlcted);
        String BS = BreastSizeSlcted + "";
        String ST = SkinTypeSlcted + "";
        String BT = ntl(BodyTypeSlcted);
        String BCM = ntl5(BodyColMainSlcted);
        String BC1 = ntl5(BodyColSub1Slcted);
        String BC2 = ntl5(BodyColSub2Slcted);
        String BC3 = ntl5(BodyColSub3Slcted);
        String FN = ntl(FaceNoseSlcted);
        String FM = ntl(FaceMouthSlcted);
        String ET = ntl(EyesSlcted);
        String EC1 = ntl5(EyeCol1Slcted);
        String EC2 = ntl5(EyeCol2Slcted);
        if (SkinTypeSlcted == 0) {
            dns = R + G + H1 + H2 + HC + BS + ST + BC1;
        } else {
            dns = R + G + H1 + H2 + HC + BS + ST + BT + BCM + BC1 + BC2 + BC3 + FN + FM + ET + EC1 + EC2;
        }

    }

    public static void setdnsForHair() {
        dns = JRMCoreH.dnsHairBSet(dns, HairSlcted);
        dns = JRMCoreH.dnsHairFSet(dns, Hair2Slcted);
        dns = JRMCoreH.dnsHairCSet(dns, ColorSlcted);
    }

    public static void HairCstmNextPreset() {
        int selct = HairPrstsSlcted + 1;
        if (selct < PresetList.size()) {
            HairPrstsSlcted = selct;
        } else {
            HairPrstsSlcted = 0;
        }

        dnsH = (String)PresetList.get(HairPrstsSlcted);
    }

    public static void HairCstmPrevPreset() {
        int selct = HairPrstsSlcted - 1;
        if (selct >= 0) {
            HairPrstsSlcted = selct;
        } else {
            HairPrstsSlcted = PresetList.size() - 1;
        }

        dnsH = (String)PresetList.get(HairPrstsSlcted);
    }

    public static void HairCstmSaveAsPreset() {
        String s = JRMCoreH.rld("HairPresets", "jinryuujrmcore.dat");
        List<String> stooges = Arrays.asList();
        if (s.length() > 3) {
            stooges = Arrays.asList(s.split(","));
        }

        ArrayList<String> saves = new ArrayList(stooges);
        ArrayList<String> defpres = new ArrayList();
        ArrayList<String> presets = new ArrayList();

        for(String def : JRMCoreH.defHairPrsts) {
            defpres.add(def);
        }

        saves.add(dnsH);
        saves.removeAll(defpres);
        String listString = "";

        for(String strng : saves) {
            listString = listString + "," + strng;
        }

        if (listString.length() > 2) {
            listString = listString.substring(1);
        }

        presets.addAll(defpres);
        presets.addAll(saves);
        PresetList = presets;
        dnsH = (String)PresetList.get(PresetList.size() - 1);
        JRMCoreH.wld(listString, "HairPresets", "jinryuujrmcore.dat", false);
        HairPrstsSlcted = PresetList.size() - 1;
        dnsH = (String)PresetList.get(HairPrstsSlcted);
    }

    public static void HairCstmDelPreset() {
        if (HairPrstsSlcted > JRMCoreH.defHairPrsts.length - 1) {
            String s = JRMCoreH.rld("HairPresets", "jinryuujrmcore.dat");
            List<String> stooges = Arrays.asList();
            if (s.length() > 3) {
                stooges = Arrays.asList(s.split(","));
            }

            ArrayList<String> saves = new ArrayList(stooges);
            ArrayList<String> defpres = new ArrayList();
            ArrayList<String> presets = new ArrayList();

            for(String def : JRMCoreH.defHairPrsts) {
                defpres.add(def);
            }

            if (saves.size() > HairPrstsSlcted - JRMCoreH.defHairPrsts.length) {
                saves.remove(HairPrstsSlcted - JRMCoreH.defHairPrsts.length);
            }

            String listString = "";

            for(String strng : saves) {
                listString = listString + "," + strng;
            }

            if (listString.length() > 2) {
                listString = listString.substring(1);
            }

            presets.addAll(defpres);
            presets.addAll(saves);
            PresetList = presets;
            JRMCoreH.wld(listString, "HairPresets", "jinryuujrmcore.dat", false);
            --HairPrstsSlcted;
            dnsH = (String)PresetList.get(HairPrstsSlcted);
        }

    }

    public static void HairCstmResetPreset() {
        dnsH = (String)PresetList.get(HairPrstsSlcted);
    }

    public static void StateViewF() {
        if (RaceSlcted == JRMCoreH.RACES) {
            ++StateSlcted;
            StateSlcted = StateSlcted > JRMCoreH.RACES + 1 ? 0 : StateSlcted;
        } else {
            StateSlcted = 0;
        }

    }

    public static void StateViewB() {
        if (RaceSlcted == JRMCoreH.RACES) {
            --StateSlcted;
            StateSlcted = StateSlcted < 0 ? JRMCoreH.RACES + 1 : StateSlcted;
        } else {
            StateSlcted = 0;
        }

    }

    public static void RaceSlctF() {
        if (JRMCoreH.isRaceArcosian(RaceSlcted) && !JGConfigRaces.CONFIG_MAJIN_ENABLED) {
            RaceSlcted = 5;
        }

        for(int selct = 1; selct < JRMCoreH.Races.length; ++selct) {
            if (RaceSlcted < selct && (JRMCoreH.RaceAllow[selct].contains("DBC") && JRMCoreH.DBC() || JRMCoreH.RaceAllow[selct].contains("HHC") && JRMCoreH.HHC())) {
                RaceSlcted = selct;
                return;
            }
        }

        int var1 = 0;
        if (RaceSlcted > var1 || JRMCoreH.isRaceMajin(RaceSlcted) && !JGConfigRaces.CONFIG_MAJIN_ENABLED) {
            RaceSlcted = var1;
        }
    }

    public static void RaceSlctB() {
        if (JRMCoreH.isRaceHuman(RaceSlcted) && !JGConfigRaces.CONFIG_MAJIN_ENABLED) {
            RaceSlcted = 5;
        }

        for(int selct = JRMCoreH.Races.length - 1; selct >= 0; --selct) {
            if (RaceSlcted > selct && JRMCoreH.Allow(JRMCoreH.RaceAllow[selct])) {
                RaceSlcted = selct;
                return;
            }
        }

        for(int var1 = JRMCoreH.Races.length - 1; var1 >= 0; --var1) {
            if (RaceSlcted < var1 && JRMCoreH.Allow(JRMCoreH.RaceAllow[var1])) {
                RaceSlcted = var1;
                return;
            }
        }

    }

    public static void GenderSlctF() {
        int selct = GenderSlcted + 1;
        if (selct < JRMCoreH.Genders.length && JRMCoreH.Allow(JRMCoreH.GenderAllow[selct])) {
            GenderSlcted = selct;
        } else {
            GenderSlcted = 0;
        }

    }

    public static void YearsSlctF() {
        int selct = YearsSlcted + 1;
        if (selct < JRMCoreH.YearsD.length) {
            YearsSlcted = selct;
        } else {
            YearsSlcted = 0;
        }

    }

    public static void YearsSlctB() {
        int selct = YearsSlcted - 1;
        if (selct >= 0) {
            YearsSlcted = selct;
        } else {
            YearsSlcted = JRMCoreH.YearsD.length - 1;
        }

    }

    public static void HairSlctF() {
        int selct = HairSlcted + 1;
        if (selct < JRMCoreH.Hairs.length) {
            HairSlcted = selct;
        } else {
            HairSlcted = JRMCoreH.isRaceMajin(RaceSlcted) ? 10 : 0;
        }

    }

    public static void HairSlctB() {
        int selct = HairSlcted - 1;
        if (selct >= 0) {
            HairSlcted = selct;
        } else {
            HairSlcted = JRMCoreH.Hairs.length - 1;
        }

    }

    public static void Hair2SlctF() {
        int selct = Hair2Slcted + 1;
        if (selct < JRMCoreH.Hairs2.length) {
            Hair2Slcted = selct;
        } else {
            Hair2Slcted = 0;
        }

    }

    public static void Hair2SlctB() {
        int selct = Hair2Slcted - 1;
        if (selct >= 0) {
            Hair2Slcted = selct;
        } else {
            Hair2Slcted = JRMCoreH.Hairs2.length - 1;
        }

    }

    public static int SlctF(int s, int l) {
        int selct = s + 1;
        return selct < l ? selct : 0;
    }

    public static int SlctB(int s, int l) {
        int selct = s - 1;
        return selct >= 0 ? selct : l - 1;
    }

    public static int Slct(String dir, int Select, String[] allow) {
        if (dir.contains("B")) {
            int selct = Select - 1;
            boolean loop = true;

            while(loop) {
                if (JRMCoreH.RaceCanHavePwr[RaceSlcted].contains("" + selct) && selct < allow.length && JRMCoreH.Allow(allow[selct])) {
                    Select = selct;
                    loop = false;
                    break;
                }

                --selct;
                if (selct <= 0) {
                    selct = allow.length - 1;
                }
            }
        } else {
            int selct = Select + 1;
            boolean loop = true;

            while(loop) {
                if (JRMCoreH.RaceCanHavePwr[RaceSlcted].contains("" + selct) && selct < allow.length && JRMCoreH.Allow(allow[selct])) {
                    Select = selct;
                    loop = false;
                    break;
                }

                ++selct;
                if (selct >= allow.length) {
                    selct = 0;
                }
            }
        }

        return Select;
    }

    public static int Slct2(String dir, int Select, String[] allow, int allowsel, String[] cur) {
        if (dir.contains("B")) {
            int selct = Select - 1;
            if (selct >= 0 && JRMCoreH.Allow(allow[allowsel])) {
                Select = selct;
            } else {
                Select = cur.length - 1;
            }
        } else {
            int selct = Select + 1;
            if (selct < cur.length && JRMCoreH.Allow(allow[allowsel])) {
                Select = selct;
            } else {
                Select = 0;
            }
        }

        return Select;
    }

    public static int SlctCol(String dir, int Select, String[] allow) {
        if (dir.contains("B")) {
            int selct = Select - 1;
            if (selct >= 0) {
                Select = selct;
            } else {
                Select = allow.length - 1;
            }
        } else {
            int selct = Select + 1;
            if (selct < allow.length) {
                Select = selct;
            } else {
                Select = 0;
            }
        }

        return Select;
    }

    public static int SlctSpec(String dir, int Select, int[] allow) {
        if (dir.contains("B")) {
            int selct = Select - 1;
            int max = allow[RaceSlcted];
            if (selct >= 0) {
                Select = selct;
            } else {
                Select = max - 1;
            }
        } else {
            int selct = Select + 1;
            int max = allow[RaceSlcted];
            if (selct < max) {
                Select = selct;
            } else {
                Select = 0;
            }
        }

        return Select;
    }

    public static void func_110423_a_I(int par0, int par1, int scale, float par3, float par4, EntityLivingBase entity) {
        func_110423_a(par0, par1, scale, par3, par4, entity, true, true, false);
    }

    public static void func_110423_a(int par0, int par1, int scale, float par3, float par4, EntityLivingBase entity) {
        func_110423_a(par0, par1, scale, par3, par4, entity, true, true, true);
    }

    public static void func_110423_a(int par0, int par1, int scale, float par3, float par4, EntityLivingBase entity, boolean hr, boolean l, boolean i) {
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par0, (float)par1, 50.0F);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        if (l) {
            RenderHelper.enableStandardItemLighting();
        }

        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        if (hr) {
            GL11.glRotatef(-((float)Math.atan((par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        }

        entity.renderYawOffset = hr ? (float)(i ? -1 : 1) * (float)Math.atan((par3 / 40.0F)) * 20.0F : 0.0F;
        entity.rotationYaw = hr ? (float)(i ? -1 : 1) * (float)Math.atan((par3 / 40.0F)) * 40.0F : 0.0F;
        entity.rotationPitch = hr ? -((float)Math.atan(par4 / 40.0F)) * 20.0F : 0.0F;
        entity.rotationYawHead = hr ? entity.rotationYaw : 0.0F;
        entity.prevRotationYawHead = hr ? entity.rotationYaw : 0.0F;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        entity.renderYawOffset = f2;
        entity.rotationYaw = f3;
        entity.rotationPitch = f4;
        entity.prevRotationYawHead = f5;
        entity.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public static String[] clDesc(int pwr) {
        String[] cl = JRMCoreH.PwrtypAllow[pwr].contains("DBC") ? JRMCoreH.ClassesDBCDesc : (JRMCoreH.PwrtypAllow[pwr].contains("NC") ? JRMCoreH.ClansDesc : JRMCoreH.ClassesDesc);
        return cl;
    }

    public static String[] clDesc() {
        return clDesc(PwrtypSlcted);
    }

    public static String[] cl() {
        return JRMCoreH.cl(PwrtypSlcted);
    }

    private static String ntl(int i) {
        return JRMCoreH.numToLet(i);
    }

    private static String ntl5(int i) {
        return JRMCoreH.numToLet5(i);
    }

    public static boolean hovered(int mX, int mY, int xpos, int ypos, int w, int h) {
        return xpos < mX && xpos + w > mX && ypos - 3 < mY && ypos + h > mY;
    }

    private void nametf(FontRenderer var8, int id, int i, int j) {
        this.inputField2[id].setMaxStringLength(3);
        this.inputField2[id].setEnableBackgroundDrawing(true);
        this.inputField2[id].setCanLoseFocus(true);
    }

    private void updateMajinHairToBodyColor() {
        if (JRMCoreH.isRaceMajin(RaceSlcted) && ColorSlcted != BodyColMainSlcted) {
            ColorSlcted = BodyColMainSlcted;
            setdnsForHair();
        }

    }

    private Tessellator getTessellator() {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        float f1 = this.width > this.height ? 120.0F / (float) this.width : 120.0F / (float) this.height;
        float f2 = (float) this.height * f1 / 256.0F;
        float f3 = (float) this.width * f1 / 256.0F;
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.width;
        int l = this.height;
        tessellator.addVertexWithUV(0.0D, l, this.zLevel, 0.5F - f2, 0.5F + f3);
        tessellator.addVertexWithUV(k, l, this.zLevel, 0.5F - f2, 0.5F - f3);
        tessellator.addVertexWithUV(k, 0.0D, this.zLevel, 0.5F + f2, 0.5F - f3);
        tessellator.addVertexWithUV(0.0D, 0.0D, this.zLevel, 0.5F + f2, 0.5F + f3);
        return tessellator;
    }

    private void rotateAndBlurSkybox() {
        mc.getTextureManager().bindTexture(this.field_110351_G);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        byte b0 = 3;

        for (int i = 0; i < b0; ++i) {
            tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float) (i + 1));
            int j = this.width;
            int k = this.height;
            float f1 = (float) (i - b0 / 2) / 256.0F;
            tessellator.addVertexWithUV(j, k, this.zLevel, 0.0F + f1, 1.0D);
            tessellator.addVertexWithUV(j, 0.0D, this.zLevel, 1.0F + f1, 1.0D);
            tessellator.addVertexWithUV(0.0D, 0.0D, this.zLevel, 1.0F + f1, 0.0D);
            tessellator.addVertexWithUV(0.0D, k, this.zLevel, 0.0F + f1, 0.0D);
        }

        tessellator.draw();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColorMask(true, true, true, true);
    }

    private void drawPanorama(float p_73970_3_) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        byte b0 = 8;

        for (int k = 0; k < b0 * b0; ++k) {
            GL11.glPushMatrix();
            float f1 = ((float) (k % b0) / (float) b0 - 0.5F) / 64.0F;
            float f2 = ((float) (k / b0) / (float) b0 - 0.5F) / 64.0F;
            float f3 = 0.0F;
            GL11.glTranslatef(f1, f2, f3);
            GL11.glRotatef(MathHelper.sin(((float) this.panoramaTimer + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-((float) this.panoramaTimer + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int l = 0; l < 6; ++l) {
                GL11.glPushMatrix();

                if (l == 1) {
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 2) {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 3) {
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 4) {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (l == 5) {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                mc.getTextureManager().bindTexture(titlePanoramaPaths[l]);
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_I(16777215, 255 / (k + 1));
                float f4 = 0.0F;
                tessellator.addVertexWithUV(-1.0D, -1.0D, 1.0D, 0.0F + f4, 0.0F + f4);
                tessellator.addVertexWithUV(1.0D, -1.0D, 1.0D, 1.0F - f4, 0.0F + f4);
                tessellator.addVertexWithUV(1.0D, 1.0D, 1.0D, 1.0F - f4, 1.0F - f4);
                tessellator.addVertexWithUV(-1.0D, 1.0D, 1.0D, 0.0F + f4, 1.0F - f4);
                tessellator.draw();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        tessellator.setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    private void renderSkybox(float p_73971_3_) {
        mc.getFramebuffer().unbindFramebuffer();
        GL11.glViewport(0, 0, 256, 256);
        this.drawPanorama(p_73971_3_);
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        this.rotateAndBlurSkybox();
        mc.getFramebuffer().bindFramebuffer(true);
        GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
        Tessellator tessellator = getTessellator();
        tessellator.draw();
    }

    static {
        wish = CustomNpcPlusDBC.ID + ":textures/gui/gui_yellow.png";
        pc = JRMCoreH.tjjrmc + ":gui_pc.png";
        gui_help_tabs = JRMCoreH.tjjrmc + ":help/tab0.png";
        wishsep = JRMCoreH.tjjrmc + ":guidev.png";
        icons = JRMCoreH.tjjrmc + ":icons.png";
        colorType = 0;
        StateSlcted = 0;
        RaceSlcted = 0;
        GenderSlcted = 0;
        YearsSlcted = 3;
        HairSlcted = 10;
        Hair2Slcted = 0;
        ColorSlcted = 0;
        BreastSizeSlcted = 4;
        SkinTypeSlcted = 0;
        BodyTypeSlcted = 0;
        BodyColPresetSlcted = 0;
        BodyColMainSlcted = 0;
        BodyColSub1Slcted = 0;
        BodyColSub2Slcted = 0;
        BodyColSub3Slcted = 0;
        FaceNoseSlcted = 0;
        FaceMouthSlcted = 0;
        EyesSlcted = 0;
        EyeColPresetSlcted = 0;
        EyeCol1Slcted = 0;
        EyeCol2Slcted = 0;
        BodyauColMainSlcted = 0;
        BodyauColSub1Slcted = 0;
        BodyauColSub2Slcted = 0;
        BodyauColSub3Slcted = 0;
        HairPrstsSlcted = 0;
        canSavePreset = true;
        BrghtSlcted = 0.8F;
        PwrtypSlcted = 0;
        ClassSlcted = 0;
        tail = true;
        KiColorSlcted = 0;
        PresetList = new ArrayList();
        tick = 0;
        dnsau = JRMCoreH.dnsau;
        dns = JRMCoreH.dns;
        dnsSent = "";
        dnsOrig = "";
        dnsH = JRMCoreH.dnsH;
        dnsHSent = "";
        dnsHOrig = "";
        detailList = new ArrayList();
        ssc = "";
        sscr = 0;
        count = 0;
        warn = 0;
        startcount = 0;
        scrlld = 0;
        titlePanoramaPaths = new ResourceLocation[]{new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_0.png"), new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_1.png"), new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_2.png"), new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_3.png"), new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_4.png"), new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_5.png")};
    }
}

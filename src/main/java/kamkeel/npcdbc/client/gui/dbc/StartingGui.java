package kamkeel.npcdbc.client.gui.dbc;


import JinRyuu.JRMCore.*;
import JinRyuu.JRMCore.items.ItemBarberSnC;
import JinRyuu.JRMCore.p.DBC.DBCPdri;
import JinRyuu.JRMCore.p.JRMCorePData2;
import JinRyuu.JRMCore.p.PD;
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
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static JinRyuu.JRMCore.JRMCoreGuiScreen.*;
import static kamkeel.npcdbc.util.Utility.t;

public class StartingGui extends AbstractJRMCGui2 {
    public static Minecraft mc = Minecraft.getMinecraft();
    public static StartingGui instance;
    public boolean guiUp = false;
    public int guiID = 0;
    public int guiIDprev = 0;
    public int guiIDprev2 = 0;
    public int guiLeft;
    public int guiTop;
    public int xSize = 256;
    public int ySize = 159;
    public float xSize_lo;
    public float ySize_lo;
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
    public static int selectedColor = 0; // Add this line to store the selected color
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
    public static String guiBG2;
    public static String pc;
    public static String gui_help_tabs;
    public static String wishsep;
    public static String icons;
    String SideMsn = "";
    int ss = -1;
    ResourceLocation guiLocation2;
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
    protected static final ResourceLocation field_110408_a;
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
    private DynamicTexture viewportTexture;
    private ResourceLocation field_110351_G;
    private int panoramaTimer;
    private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[]{
        new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_0.png"),
        new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_1.png"),
        new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_2.png"),
        new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_3.png"),
        new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_4.png"),
        new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/background/panorama_5.png")};

    public boolean isGUIOpen(int id) {
        return id == this.guiID;
    }

    public StartingGui() {
        JRMCoreGuiSliderX00.sliderValue = 0.0F;
        this.scroll = 0;
        scrollSide = 0.0F;
        this.guiIDprev = this.guiID;
        ssc = "";
        this.confirmationWindow = false;
        this.guiUp = false;
        if (JRMCoreConfig.ssc.contains("http://")) {
            JRMCoreH.turih.remove(JRMCoreConfig.ssc);
            JRMCoreH.tur(JRMCoreConfig.ssc);
        }

        String bpmd = JRMCoreH.rld("BPMode", "jinryuujrmcore.dat");
        JRMCoreH.BPMode = !bpmd.equalsIgnoreCase("Normal") && bpmd.equalsIgnoreCase("High") ? 1 : 0;
    }

    public void initGui() {
        this.guiUp = false;
        this.viewportTexture = new DynamicTexture(256, 256);
        this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);

        int posX;
        for (posX = 0; posX < this.inputField2.length; ++posX) {
            (this.inputField2[posX] = new GuiTextField(mc.fontRenderer, 0, 0, 50, 12)).setText("255");
        }

        instance = this;
        int posY;
        int dam;
        int effect;
        int cost;
        int casttime;
        int cooldown;
        int j;
        int density;
        int sincantation;
        int sfire;
        int smove;
        if (JRMCoreH.Pwrtyp == 2) {
            posX = JRMCoreH.techNCBase[3];
            posY = JRMCoreH.techNCBase[4];
            dam = JRMCoreH.techNCBase[5];
            effect = JRMCoreH.techNCBase[6];
            cost = JRMCoreH.techNCBase[7];
            casttime = JRMCoreH.techNCBase[8];
            cooldown = JRMCoreH.techNCBase[9];
            j = JRMCoreH.techNCBase[10];
            density = JRMCoreH.techNCBase[11];
            sincantation = JRMCoreH.techNCBase[12];
            sfire = JRMCoreH.techNCBase[13];
            smove = JRMCoreH.techNCBase[14];
            techCrt = new int[]{1, acquired, 1, posX, posY, dam, effect, cost, casttime, cooldown, j, density, sincantation, sfire, smove};
        }

        if (JRMCoreH.Pwrtyp == 1) {
            posX = JRMCoreH.techBase[3];
            posY = JRMCoreH.techBase[4];
            dam = JRMCoreH.techBase[5];
            effect = JRMCoreH.techBase[6];
            cost = JRMCoreH.techBase[7];
            casttime = JRMCoreH.techBase[8];
            cooldown = JRMCoreH.techBase[9];
            j = JRMCoreH.techBase[10];
            density = JRMCoreH.techBase[11];
            sincantation = JRMCoreH.techBase[12];
            sfire = JRMCoreH.techBase[13];
            smove = JRMCoreH.techBase[14];
            techCrt = new int[]{1, acquired, 1, posX, posY, dam, effect, cost, casttime, cooldown, j, density, sincantation, sfire, smove};
        }

        this.buttonList.clear();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        posX = this.width / 2;
        posY = this.height / 2;
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
        String[] var19 = JRMCoreH.defHairPrsts;
        density = var19.length;

        for (sincantation = 0; sincantation < density; ++sincantation) {
            String def = var19[sincantation];
            defpres.add(def);
        }

        saves.removeAll(defpres);
        presets.addAll(defpres);
        presets.addAll(saves);
        PresetList = presets;

        for (j = 0; j < PresetList.size(); ++j) {
            if ((PresetList.get(j)).equals(dnsH)) {
                HairPrstsSlcted = j;
                break;
            }
        }

        if (JRMCoreH.SAOC()) {
            JRMCoreHSAC.initGui();
        }
    }

    public Object actionPerformed(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        int selct = par1 - 20;
        int KA;
        GuiButton ret = new JRMCoreGuiButtons00(par1, par2, par3, par4, par5, par6Str, 0);
        return ret;
    }

    public void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        int id = button.id;
        int i7;
        int i8;

        if (id == 10) {
            mc.thePlayer.closeScreen();
        }

        if (id == 11) {
//            this.guiID = 2;
            JRMCoreGuiScreen DBCScreen = new JRMCoreGuiScreen(0);
            ((IDBCGuiScreen) DBCScreen).setGuiIDPostInit(2);
            FMLCommonHandler.instance().showGuiScreen(DBCScreen);
        }

        if (button.id == 1) {
            RaceSlctF();
            JRMCoreH.Char((byte) 0, (byte) RaceSlcted);
            setchangerace();
            setdns();
        }

        if (button.id == -1) {
            RaceSlctB();
            JRMCoreH.Char((byte) 0, (byte) RaceSlcted);
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
            this.guiID = button.id - 5080;
        }

        if (id == 4 || id == 5003 || id == 5004 || id == 5005 || id == 5009 || id == 5010 || id == 5014 || id == 5015 || id == 5016 || id == 5017 || id == 5018 || id == 5019) {
            this.inputField2Cl = id;
            this.guiIDprev = this.guiID;
            this.guiID = 1;
//            JRMCoreGuiScreen DBCScreen = new JRMCoreGuiScreen(0);
//            ((IDBCGuiScreen) DBCScreen).setGuiIDPostInit(1);
//            FMLCommonHandler.instance().showGuiScreen(DBCScreen);
            colorType = button.id;
        }

        if (button.id == 4999) {
            StateViewF();
            JRMCoreH.Char((byte) 106, (byte) StateSlcted);
            setdns();
        }

        if (button.id == -4999) {
            StateViewB();
            JRMCoreH.Char((byte) 106, (byte) StateSlcted);
            setdns();
        }

        if (button.id == 5001) {
            BreastSizeSlcted = (int) (((JRMCoreGuiSlider01) button).sliderValue * 10.0F);
            setdns();
        }
        if (button.id == 8) {
            YearsSlctF();
            setdns();
            JRMCoreH.Char((byte) 7, (byte) YearsSlcted);
        }

        if (button.id == -8) {
            YearsSlctB();
            setdns();
            JRMCoreH.Char((byte) 7, (byte) YearsSlcted);
        }
        if (button.id == 106) {
            if (tail) {
                tail = false;
            } else {
                tail = true;
            }

            JRMCoreH.Char((byte) 103, (byte) (tail ? 1 : 0));
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

        if (id == 4 || id == 5003 || id == 5004 || id == 5005 || id == 5009 || id == 5010 || id == 5014 || id == 5015 || id == 5016 || id == 5017 || id == 5018 || id == 5019) {
            this.inputField2Cl = id;
            this.guiIDprev = this.guiID;
            this.guiID = 1;
            colorType = button.id;
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

        if (button.id == 33000) {
            updateColorRGB();
        }

        if (button.id == 20) {
            BrghtSlcted = ((JRMCoreGuiSlider00) button).sliderValue;
        }

        int k;
        if (button.id == -4) {
            k = ((JRMCoreGuiButtonC) button).getBgCol();
            switch (colorType) {
                case 4:
                    ColorSlcted = k;
                    break;
                case 5003:
                    BodyColMainSlcted = k;
                    break;
                case 5004:
                    BodyColSub1Slcted = k;
                    break;
                case 5005:
                    BodyColSub2Slcted = k;
                    break;
                case 5009:
                    EyeCol1Slcted = k;
                    break;
                case 5010:
                    EyeCol2Slcted = k;
                    break;
                case 5014:
                    BodyColSub3Slcted = k;
                    break;
                case 5015:
                    KiColorSlcted = k;
                    break;
                case 5016:
                    BodyauColMainSlcted = k;
                    setdnsau();
                    JRMCoreH.jrmcDataFC(3, dnsau);
                    break;
                case 5017:
                    BodyauColSub1Slcted = k;
                    setdnsau();
                    JRMCoreH.jrmcDataFC(3, dnsau);
                    break;
                case 5018:
                    BodyauColSub2Slcted = k;
                    setdnsau();
                    JRMCoreH.jrmcDataFC(3, dnsau);
                    break;
                case 5019:
                    BodyauColSub3Slcted = k;
                    setdnsau();
                    JRMCoreH.jrmcDataFC(3, dnsau);
                    break;
            }
            setdns();
            this.updateMajinHairToBodyColor();
        }


        if (button.id == 0) {
            colorType = 0;
            this.guiID = 0;
        }
    }


    @SuppressWarnings("unused")
    public void drawScreen(int x, int y, float f) {

        int ar;
        if (this.kdf < 2000) {
            ++this.kdf;
        }

        if (Mouse.isButtonDown(0)) {
            this.mousePressed = true;
            scrollSide = JRMCoreGuiSliderX00.sliderValue;
        } else {
            this.mousePressed = false;

            while (Mouse.next()) {
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

        this.xSize_lo = (float) x;
        this.ySize_lo = (float) y;
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
        boolean xSize;
        int xSize2;
        int guiLeft;
        int guiLeft2;
        int guiTop2;


        ResourceLocation guiLocation;
        int i7;
        int i8;
        byte nr;
        String mod3;
        String[] array4;
        String[] techEffects;
        int xPos;
        String[] cSklsNms;
        int limit2;
        int possibleReward2;
        boolean sz;
        int reward2;
        int ni3;
        String TransNms2;
        String StatIncreaseDesc2;
        String s13;
        boolean ssm;
        int skillID;
        int i18;
        int i5;
        String className2;
        int xPos2;
        int sw5;
        String Attributes;
        String TP;
        boolean px;
        int n5;

        String ln;
        String s11;
        int gender;
        boolean n24;
        int i22;
        String[] techTypes;
        boolean COLOR_ID;
        int type2;
        float h20;
        String ClassDesc2;
        String Level;
        String Class3;
        String Stats2;
        boolean v3;
        boolean doit2;
        String buttonText;
        int i9;
        boolean fc;
        String TRState2;
        boolean canAffordMind;
        int cls;
        int var10000;
        float h18;
        float h19;
        int i15;
        int STR2;
        String powerType;
        int xw;
        String raceName;
        boolean gn;
        String Race2;
        String mod4;
        String[] j3;
        int costTp4;
        String StatIncreaseDesc5;
        String TrainingPoints;
        String RequiredTP;
        String Alignment;
        boolean upg;
        String name3;
        float h2;
        float h3;
        float posXm;
        int fcs;
        String StatIncreaseDesc3;
        float h4;
        float posYm;
        int sw6;
        String StatIncreaseDesc6;
        this.renderSkybox(x, y, f);
        if (this.isGUIOpen(0)) {
            guiLeft = (this.width - 80) / 2;
            guiLeft2 = (this.height - 107) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_ALPHA_TEST);

            int gui2Left = guiLeft - 131;
            int gui2Left2 = guiLeft2 - 20;

            mc.renderEngine.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID, "textures/gui/gui.png"));
            this.drawTexturedModalRect(guiLeft, guiLeft2, 123, 1, 80, 120);
            this.drawTexturedModalRect(gui2Left, gui2Left2, 1, 1, 120, 140);
            this.drawTexturedModalRect(guiLeft + 101, guiLeft2 - 20, 1, 143, 160, 100);
            this.buttonList.add(new JRMCoreGuiButtons00(10, guiLeft + 140, guiLeft2 + 88, 20, 20, "X", 4210752));

            ln = JRMCoreH.trl("jrmc", "Next");
            i8 = this.fontRendererObj.getStringWidth(ln) + 8;
            String rct = t("dbcimpactgui.rcdes");
            //var8.drawString(rct, guiLeft + 106, guiLeft2 - 15, 20);
            var8.drawString(rct, guiLeft + 106, guiLeft2 - 19, 20);

            String rdt1 = t("dbcimpactgui.rcH1");
            if (JRMCoreH.Races[RaceSlcted].equals("Saiyan")) {
                rdt1 = t("dbcimpactgui.rcSaL1");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Half-Saiyan")) {
                rdt1 = t("dbcimpactgui.rcHsL1");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Namekian")) {
                rdt1 = t("dbcimpactgui.rcNaL1");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Arcosian")) {
                rdt1 = t("dbcimpactgui.rcArL1");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Majin")) {
                rdt1 = t("dbcimpactgui.rcMaL1");
            }
            var8.drawString(rdt1, guiLeft + 103, guiLeft2 - 5, 20);

            rdt1 = t("dbcimpactgui.rcH2");
            if (JRMCoreH.Races[RaceSlcted].equals("Saiyan")) {
                rdt1 = t("dbcimpactgui.rcSaL2");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Half-Saiyan")) {
                rdt1 = t("dbcimpactgui.rcHsL2");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Namekian")) {
                rdt1 = t("dbcimpactgui.rcNaL2");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Arcosian")) {
                rdt1 = t("dbcimpactgui.rcArL2");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Majin")) {
                rdt1 = t("dbcimpactgui.rcMaL2");
            }
            var8.drawString(rdt1, guiLeft + 103, guiLeft2 + 5, 20);

            rdt1 = t("dbcimpactgui.rcH3");
            if (JRMCoreH.Races[RaceSlcted].equals("Saiyan")) {
                rdt1 = t("dbcimpactgui.rcSaL3");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Half-Saiyan")) {
                rdt1 = t("dbcimpactgui.rcHsL3");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Namekian")) {
                rdt1 = t("dbcimpactgui.rcNaL3");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Arcosian")) {
                rdt1 = t("dbcimpactgui.rcArL3");
            } else if (JRMCoreH.Races[RaceSlcted].equals("Majin")) {
                rdt1 = t("dbcimpactgui.rcMaL3");
            }
            var8.drawString(rdt1, guiLeft + 103, guiLeft2 + 15, 20);

            this.buttonList.add(new JRMCoreGuiButtons00(11, guiLeft + 184, guiLeft2 + 88, i8, 20, ln, 4210752));
            i5 = JRMCoreH.dnsRace("dns");
            if (JRMCoreH.Allow(JRMCoreH.RaceAllow[RaceSlcted])) {
                CanRace = true;
            } else {
                CanRace = false;
                if (RaceSlcted != 0) {
                    RaceSlcted = 0;
                    JRMCoreH.Char((byte) 0, (byte) RaceSlcted);
                }
                if (RaceSlcted == 2 || RaceSlcted == 5) {
                    RaceSlcted = 0;
                    JRMCoreH.Char((byte) 0, (byte) RaceSlcted);
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

            mod3 = JRMCoreH.trl("jrmc", JRMCoreH.Races[RaceSlcted]);
            powerType = JRMCoreH.trl("jrmc", JRMCoreH.Genders[GenderSlcted]);
            raceName = JRMCoreH.trl("jrmc", JRMCoreH.Years[YearsSlcted]);
            Race2 = JRMCoreH.trl("jrmc", "TRState");
            className2 = JRMCoreH.trl("jrmc", "Hair") + " " + (HairSlcted + 1);
            Class3 = "" + ColorSlcted;
            ClassDesc2 = JRMCoreH.trl("jrmc", JRMCoreH.skinTyps[SkinTypeSlcted]);
            Level = JRMCoreH.trl("jrmc", "Tail");
            i9 = 0;
            if (CanRace) {
                this.buttonList.add(new DBCImpactGUIButtonsA(-1, guiLeft - 21, guiLeft2 + 122, "<"));
                this.buttonList.add(new DBCImpactGUIButtonsA(1, guiLeft + 91, guiLeft2 + 122, ">"));
            }
            var8.drawString(mod3, guiLeft + 41 - var8.getStringWidth(mod3) / 2, guiLeft2 + 123, 0);
            i9 = i9 + 1;
            if (CanGender) {
                this.buttonList.add(new DBCImpactGUIButtonsA(-2, guiLeft - 11, guiLeft2 + 135, "<"));
                this.buttonList.add(new DBCImpactGUIButtonsA(2, guiLeft + 81, guiLeft2 + 135, ">"));
                var8.drawString(powerType, guiLeft + 41 - var8.getStringWidth(powerType) / 2, guiLeft2 + 136, 0);
            }

            ++i9;
            if (CanHair) {
                this.buttonList.add(new DBCImpactGUIButtonsA(-3, gui2Left, gui2Left2, "<"));
                this.buttonList.add(new DBCImpactGUIButtonsA(3, gui2Left + 110, gui2Left2, ">"));
                if (HairSlcted != 12) {
                    var8.drawString(className2, gui2Left + 60 - var8.getStringWidth(className2) / 2, gui2Left2 + 1, 0);
                }

                buttonText = JRMCoreH.trl("jrmc", "CustomHair");
                ni3 = this.fontRendererObj.getStringWidth(buttonText) / 2;
                if (HairSlcted == 12) {
                    this.buttonList.add((new JRMCoreGuiButtons01(5100, gui2Left + 60 - ni3, gui2Left2 + 1, ni3, buttonText, JRMCoreH.techNCCol[1])).setShadow(false));
                }
            }

            ++i9;
            if (CanColor) {
                buttonText = JRMCoreH.trl("jrmc", "Color");
                ni3 = this.fontRendererObj.getStringWidth(buttonText) / 2;
                this.buttonList.add((new JRMCoreGuiButtons01(4, gui2Left + 60 - ni3, gui2Left2 + 11, ni3, buttonText, JRMCoreH.techNCCol[1])).setShadow(false));
            }

            if (RaceSlcted != 4 && RaceSlcted != 3) {
                h2 = (float) (ColorSlcted >> 16 & 255) / 255.0F;
                h3 = (float) (ColorSlcted >> 8 & 255) / 255.0F;
                h4 = (float) (ColorSlcted & 255) / 255.0F;
                posXm = 1.0F;
                buttonText = JRMCoreH.trl("jrmc", "Color");
                ni3 = this.fontRendererObj.getStringWidth(buttonText) / 2;
                GL11.glColor4f(1.0F * h2, 1.0F * h3, 1.0F * h4, 1.0F);
                mc.renderEngine.bindTexture(new ResourceLocation(button1));
                this.drawTexturedModalRect(gui2Left + 50 - ni3, gui2Left2 + 10, 0, 0, this.fontRendererObj.getStringWidth(buttonText) + 20, 10);
            } else if (RaceSlcted == 4) {
                buttonText = Race2 + ": " + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", JRMCoreH.TransNms[RaceSlcted][JRMCoreH.State]);
                this.buttonList.add(new DBCImpactGUIButtonsA(-4999, gui2Left, gui2Left2 + 10, "<"));
                this.buttonList.add(new DBCImpactGUIButtonsA(4999, gui2Left + 110, gui2Left2 + 10, ">"));
                var8.drawString(buttonText, gui2Left + 60 - var8.getStringWidth(buttonText) / 2, gui2Left2 + 11, 0);
            }

            ++i9;
            if (GenderSlcted == 1) {
                this.buttonList.add(new JRMCoreGuiSlider01(5001, guiLeft + 11 - var8.getStringWidth(powerType) / 2, guiLeft2 + 146, 50, 10, "", (float) BreastSizeSlcted * 0.1F, 1.0F));
            }

            ++i9;
            if (CanYears) {
                this.buttonList.add(new DBCImpactGUIButtonsA(-8, gui2Left, gui2Left2 + 20, "<"));
                this.buttonList.add(new DBCImpactGUIButtonsA(8, gui2Left + 110, gui2Left2 + 20, ">"));
                var8.drawString(raceName, gui2Left + 60 - var8.getStringWidth(raceName) / 2, gui2Left2 + 21, 0);
            }

            ++i9;
            if (RaceSlcted == 2) {
                buttonText = Level + " " + (tail ? JRMCoreH.trl("jrmc", "Enabled") : JRMCoreH.trl("jrmc", "Disabled"));
                ni3 = this.fontRendererObj.getStringWidth(buttonText) / 2;
                this.buttonList.add((new JRMCoreGuiButtons01(106, gui2Left + 60 - ni3, gui2Left2 + 31, ni3, buttonText, tail ? 3452672 : 4210752)).setShadow(false));
            }

            ++i9;
            if (canCustom) {
                this.buttonList.add(new DBCImpactGUIButtonsA(-5002, gui2Left, gui2Left2 + 40, "<"));
                this.buttonList.add(new DBCImpactGUIButtonsA(5002, gui2Left + 110, gui2Left2 + 40, ">"));
            }

            var8.drawString(ClassDesc2, gui2Left + 60 - var8.getStringWidth(ClassDesc2) / 2, gui2Left2 + 41, 0);
            ++i9;
            fc = false;
            if (SkinTypeSlcted == 1) {
                if (SkinTypeSlcted == 1) {
                    TRState2 = (BodyTypeSlcted + 1) + "";
                    var8.drawString(TRState2, guiLeft + 85, guiLeft2 + 16, 0);
                    this.buttonList.add(new DBCImpactGUIButtonsA(-5, guiLeft + 82, guiLeft2 + 2, "^"));
                    this.buttonList.add(new DBCImpactGUIButtonsA(5, guiLeft + 82, guiLeft2 + 26, "v"));
                    ++i9;
                    this.buttonList.add(new DBCImpactGUIButtonsA(-5012, guiLeft + 82, guiLeft2 + 2, "^"));
                    this.buttonList.add(new DBCImpactGUIButtonsA(5012, guiLeft + 82, guiLeft2 + 26, "v"));
                    cls = JRMCoreH.customSknLimits[RaceSlcted][1];
                    if (cls >= 1) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5003, (guiLeft + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0))) - 150, (guiLeft2 + 5 - 1 + i9 * 10) - 109, 20, 10, "", BodyColMainSlcted));
                    }

                    if (cls >= 2) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5004, (guiLeft + 190 - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 21) - 150, (guiLeft2 + 5 - 1 + i9 * 10) - 109, 20, 10, "", BodyColSub1Slcted));
                    }

                    if (cls >= 3) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5005, (guiLeft + 190 - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 42) - 150, (guiLeft2 + 5 - 1 + i9 * 10) - 109, 20, 10, "", BodyColSub2Slcted));
                    }

                    if (cls >= 4) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5014, (guiLeft + 190 - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 63) - 150, (guiLeft2 + 5 - 1 + i9 * 10) - 109, 20, 10, "", BodyColSub3Slcted));
                    }

                    ++i9;
                    fcs = i9;
                    this.buttonList.add(new DBCImpactGUIButtonsA(-5006, gui2Left, gui2Left2 + 70, "<"));
                    this.buttonList.add(new DBCImpactGUIButtonsA(5006, gui2Left + 110, gui2Left2 + 70, ">"));
                    StatIncreaseDesc3 = JRMCoreH.trl("jrmc", "Nose") + " " + (FaceNoseSlcted + 1);
                    var8.drawString(StatIncreaseDesc3, gui2Left + 60 - var8.getStringWidth(StatIncreaseDesc3) / 2, gui2Left2 + 71, 0);
                    ++i9;
                    StatIncreaseDesc3 = JRMCoreH.trl("jrmc", "Mouth") + " " + (FaceMouthSlcted + 1);
                    var8.drawString(StatIncreaseDesc3, gui2Left + 60 - var8.getStringWidth(StatIncreaseDesc3) / 2, gui2Left2 + 91, 0);
                    this.buttonList.add(new DBCImpactGUIButtonsA(-5007, gui2Left, gui2Left2 + 90, "<"));
                    this.buttonList.add(new DBCImpactGUIButtonsA(5007, gui2Left + 110, gui2Left2 + 90, ">"));
                    ++i9;
                    StatIncreaseDesc3 = JRMCoreH.trl("jrmc", "Eyes") + " " + (EyesSlcted + 1);
                    var8.drawString(StatIncreaseDesc3, gui2Left + 60 - var8.getStringWidth(StatIncreaseDesc3) / 2, gui2Left2 + 101, 0);
                    this.buttonList.add(new DBCImpactGUIButtonsA(-5008, gui2Left, gui2Left2 + 100, "<"));
                    this.buttonList.add(new DBCImpactGUIButtonsA(5008, gui2Left + 110, gui2Left2 + 100, ">"));
                    ++i9;
                    cls = JRMCoreH.customSknLimits[RaceSlcted][5];
                    if (cls != 0) {
                        this.buttonList.add(new DBCImpactGUIButtonsA(-5013, gui2Left, gui2Left2 + 110, "<"));
                        this.buttonList.add(new DBCImpactGUIButtonsA(5013, gui2Left + 110, gui2Left2 + 110, ">"));
                    }

                    if (cls >= 1) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5009, (gui2Left + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0))) - 131, (gui2Left2 + 5 - 1 + i9 * 10) - 24, 20, 10, "", EyeCol1Slcted));
                    }

                    if (cls >= 2) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5010, (gui2Left + 190 - 10 + (cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 21) - 131, (gui2Left2 + 5 - 1 + i9 * 10) - 24, 20, 10, "", EyeCol2Slcted));
                        ++i9;
                        s13 = JRMCoreH.trl("jrmc", "Match");
                        sw5 = this.fontRendererObj.getStringWidth(s13) / 2;
                        this.buttonList.add((new JRMCoreGuiButtons01(5011, (gui2Left + 190 - sw5) - 131, (gui2Left2 + 5 + i9 * 10) - 24, sw5, s13, JRMCoreH.techNCCol[1])).setShadow(false));
                    }

                    ++i9;
                    fc = hovered(x, y, (gui2Left + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0))) - 131, (gui2Left2 + 5 - 1 + i9 * 10) - 24, 120, 7 + i9 * 3);
                }
            } else {
                ni3 = JRMCoreH.customSknLimits[RaceSlcted][1];
                if (ni3 >= 2) {
                    this.buttonList.add(new JRMCoreGuiButtonC1(5004, guiLeft + 30, guiLeft2 - 15, 20, 10, "", BodyColSub1Slcted));
                }
            }

            if (RaceSlcted == 1 && !tail) {
                tail = true;
                JRMCoreH.Char((byte) 103, (byte) (true ? 1 : 0));
            }

            JRMCoreClient.mc.mouseHelper.mouseXYChange();
            posXm = (float) Mouse.getX() * 1.0F / ((float) JRMCoreClient.mc.displayWidth * 1.0F);
            posYm = (float) Mouse.getY() * 1.0F / ((float) JRMCoreClient.mc.displayHeight * 1.0F);
            var10000 = (int) ((float) var6 * posXm);
            var10000 = var7 - (int) ((float) var7 * posYm);

            func_110423_a_I(guiLeft + 40, guiLeft2 + 118, 60, (float) (guiLeft + 51) - this.xSize_lo, (float) (guiLeft2 + 80) - this.ySize_lo, mc.thePlayer);

            StatIncreaseDesc6 = JRMCoreH.trl("jrmc", "Appearance");
            sw6 = this.fontRendererObj.getStringWidth(StatIncreaseDesc6) / 2;
            var8.drawString(StatIncreaseDesc6, gui2Left + 60 - sw6, gui2Left2 - 12, 0);
        }


        if (this.isGUIOpen(1)) {
            guiLeft2 = (this.width - 256) / 2;
            guiTop2 = (this.height - 159) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            //new ResourceLocation(CustomNpcPlusDBC.ID + ":textures/gui/gui_dark.png");
            mc.renderEngine.bindTexture(new ResourceLocation(CustomNpcPlusDBC.ID, "textures/gui/gui_dark.png"));
//            guiLocation2 = new ResourceLocation(wish);
//            mc.renderEngine.bindTexture(guiLocation2);
            this.drawTexturedModalRect(guiLeft2, guiTop2, 0, 0, 256, 159);
            gn = false;

            for (i5 = 0; i5 < 128; ++i5) {
                for (gender = 0; gender < 128; ++gender) {
                    Color i10 = Color.getHSBColor((float) i5 * 2.0F / 255.0F, (float) gender * 2.0F / 255.0F, BrghtSlcted);
                    i22 = i10.getRed() * 65536 + i10.getGreen() * 256 + i10.getBlue();
                    if (colorType == 5015 && i22 == 0) {
                        i8 = 11075583;
                        if (JRMCoreH.align <= 66 && JRMCoreH.align >= 33) {
                            i8 = 14526719;
                        }

                        if (JRMCoreH.align < 33) {
                            i8 = 16646144;
                        }

                        this.buttonList.add(new JRMCoreGuiButtonC(-4, guiLeft2 + 5 + i5, guiTop2 + 5 + gender, 1, 1, "", i22, i8));
                    } else {
                        this.buttonList.add(new JRMCoreGuiButtonC(-4, guiLeft2 + 5 + i5, guiTop2 + 5 + gender, 1, 1, "", i22));
                    }
                }
            }

            if (colorType == 5015 && BrghtSlcted == 0.0F) {
                var8.drawString(JRMCoreH.trl("jrmc", "AlignmentBased"), guiLeft2 + 5, guiTop2 + 140, 0);
            }

            this.buttonList.add(new JRMCoreGuiSlider00(20, guiLeft2 + 135, guiTop2 + 5, "", BrghtSlcted, 1.0F));
            i5 = 0;
            if (this.inputField2Cl == 4) {
                i5 = ColorSlcted;
            } else if (this.inputField2Cl == 5015) {
                i5 = KiColorSlcted;
            } else if (this.inputField2Cl == 5003) {
                i5 = BodyColMainSlcted;
            } else if (this.inputField2Cl == 5004) {
                i5 = BodyColSub1Slcted;
            } else if (this.inputField2Cl == 5005) {
                i5 = BodyColSub2Slcted;
            } else if (this.inputField2Cl == 5014) {
                i5 = BodyColSub3Slcted;
            } else if (this.inputField2Cl == 5009) {
                i5 = EyeCol1Slcted;
            } else if (this.inputField2Cl == 5010) {
                i5 = EyeCol2Slcted;
            } else if (this.inputField2Cl == 5016) {
                i5 = BodyauColMainSlcted;
            } else if (this.inputField2Cl == 5017) {
                i5 = BodyauColSub1Slcted;
            } else if (this.inputField2Cl == 5018) {
                i5 = BodyauColSub2Slcted;
            } else if (this.inputField2Cl == 5019) {
                i5 = BodyauColSub3Slcted;
            }

            float h6 = (float) (i5 >> 16 & 255) / 255.0F;
            h18 = (float) (i5 >> 8 & 255) / 255.0F;
            h19 = (float) (i5 & 255) / 255.0F;
            h20 = 1.0F;
            GL11.glColor4f(1.0F * h6, 1.0F * h18, 1.0F * h19, 1.0F);
            mc.renderEngine.bindTexture(new ResourceLocation(button1));
            this.drawTexturedModalRect(guiLeft2 + 180, guiTop2 + 65, 0, 0, 50, 10);
            j3 = new String[]{"Red", "Green", "Blue"};

            for (xPos2 = 0; xPos2 < this.inputField2.length; ++xPos2) {
                ClassDesc2 = j3[xPos2] + ":";
                possibleReward2 = this.fontRendererObj.getStringWidth(ClassDesc2) / 2;
                var8.drawString(ClassDesc2, guiLeft2 + 158, guiTop2 + 15 + xPos2 * 15, 0);
                this.nametf(var8, xPos2, 0, 0);
                if (this.inputField2[xPos2] != null) {
                    this.inputField2[xPos2].xPosition = guiLeft2 + 195;
                    this.inputField2[xPos2].yPosition = guiTop2 + 15 + xPos2 * 15;
                    this.inputField2[xPos2].drawTextBox();
                }
            }

            Class3 = "Get RGB Color";
            limit2 = this.fontRendererObj.getStringWidth("Get RGB Color") / 2;
            this.buttonList.add((new JRMCoreGuiButtons01(33000, guiLeft2 + 205 - limit2, guiTop2 + 80, limit2, "Get RGB Color", JRMCoreH.techNCCol[1])).setShadow(false));
            Level = JRMCoreH.trl("jrmc", "Back");
            i9 = this.fontRendererObj.getStringWidth(Level) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(0, posX - 130 - i9, posY + 65, i9, 20, Level, 0));
        }

        super.drawScreen(x, y, f);
        this.drawDetails(x, y, var8);
    }


    public void updateScreen() {
        ++this.panoramaTimer;
        if (this.inputField != null) {
            this.inputField.updateCursorCounter();
        }

        for (int id = 0; id < this.inputField2.length; ++id) {
            if (this.inputField2 != null && this.inputField2[id] != null) {
                this.inputField2[id].updateCursorCounter();
            }
        }

    }

    public static boolean hovered(int mX, int mY, int xpos, int ypos, int w, int h) {
        return xpos < mX && xpos + w > mX && ypos - 3 < mY && ypos + h > mY;
    }

    public static void drawDetails(String s1, int xpos, int ypos, int w, int h, int x, int y, FontRenderer var8) {
        if (hovered(x, y, xpos, ypos, w, h)) {
            int ll;
            Object[] txt = new Object[]{s1, "§8", 0, true, x + 5, y + 5, 200};
            detailList.add(txt);
        }

    }

    private void drawDetails(int x, int y, FontRenderer var8) {
        if (!detailList.isEmpty()) {
            Object[] o = (Object[]) ((Object[]) detailList.get(0));
            String desc = (String) o[0];
            int ll = Integer.parseInt("" + o[6]);
            int descw = var8.getStringWidth(desc);
            int var10000 = 1 + var8.getStringWidth(desc) / ll;
            mc.renderEngine.bindTexture(new ResourceLocation("jinryuumodscore:allw.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
            int desch = JRMCoreH.txt(desc, (String) o[1], Integer.parseInt("" + o[2]), false, 0, 0, ll);
            ScaledResolution var9 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            int var10 = var9.getScaledWidth();
            int var11 = var9.getScaledHeight();
            int xp = 0;
            int yp = 0;
            if (var10 < x + (descw < ll ? descw : ll) + 10) {
                xp += var10 - (x + (descw < ll ? descw : ll)) - 10;
            }

            if (var11 < y + desch * 10 + 10) {
                yp = -(desch * 10 + 20);
            }

            this.drawTexturedModalRect(x + xp, y + 10 + yp, 5, 5, (descw < ll ? descw : ll) + 10, desch * 10 + 10);
            JRMCoreH.txt(desc, (String) o[1], Integer.parseInt("" + o[2]), Boolean.parseBoolean("" + o[3]), Integer.parseInt("" + o[4]) + xp, Integer.parseInt("" + o[5]) + 10 + yp, ll);
            detailList.clear();
        }

    }

    public static int y4NUu(String l) {
        String w = "0123456789ABCDEF";
        l = l.toUpperCase();
        int a = 0;

        for (int i = 0; i < l.length(); ++i) {
            char c = l.charAt(i);
            int d = "0123456789ABCDEF".indexOf(c);
            a = 16 * a + d;
        }

        return a;
    }

    protected void keyTyped(char c, int i) {
        super.keyTyped(c, i);
        if (this.inputField != null) {
            this.inputField.textboxKeyTyped(c, i);
        }

        for (int id = 0; id < this.inputField2.length; ++id) {
            if (this.inputField2 != null && this.inputField2[id] != null) {
                this.inputField2Ch = true;
                this.inputField2[id].textboxKeyTyped(c, i);
            }
        }

    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        if (this.inputField != null) {
            this.inputField.mouseClicked(i, j, k);
        }

        for (int id = 0; id < this.inputField2.length; ++id) {
            if (this.inputField2 != null && this.inputField2[id] != null) {
                this.inputField2[id].mouseClicked(i, j, k);
            }
        }

    }

    public static void head(int par0, int par1, int par2, float par3, float par4, EntityLivingBase par5EntityLivingBase) {
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par0, (float) par1, 50.0F);
        GL11.glScalef((float) (-par2), (float) par2, (float) par2);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = par5EntityLivingBase.renderYawOffset;
        float f3 = par5EntityLivingBase.rotationYaw;
        float f4 = par5EntityLivingBase.rotationPitch;
        float f5 = par5EntityLivingBase.prevRotationYawHead;
        float f6 = par5EntityLivingBase.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float) Math.atan((double) (par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        par5EntityLivingBase.renderYawOffset = (float) Math.atan((double) (par3 / 40.0F)) * 20.0F;
        par5EntityLivingBase.rotationYaw = (float) Math.atan((double) (par3 / 40.0F)) * 40.0F;
        par5EntityLivingBase.rotationPitch = -((float) Math.atan((double) (par4 / 40.0F))) * 20.0F;
        par5EntityLivingBase.rotationYawHead = par5EntityLivingBase.rotationYaw;
        par5EntityLivingBase.prevRotationYawHead = par5EntityLivingBase.rotationYaw;
        GL11.glTranslatef(0.0F, par5EntityLivingBase.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        renderEntityWithPosYaw(par5EntityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        par5EntityLivingBase.renderYawOffset = f2;
        par5EntityLivingBase.rotationYaw = f3;
        par5EntityLivingBase.rotationPitch = f4;
        par5EntityLivingBase.prevRotationYawHead = f5;
        par5EntityLivingBase.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public static void renderEntityWithPosYaw(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        Render render = null;

        try {
            render = RenderManager.instance.getEntityRenderObject(par1Entity);
            if (render != null && RenderManager.instance.renderEngine != null) {
                try {
                    render.doRender(par1Entity, par2, par4, par6, par8, par9);
                } catch (Throwable var12) {
                    throw new ReportedException(CrashReport.makeCrashReport(var12, "Rendering entity in world"));
                }
            }

        } catch (Throwable var13) {
            CrashReport crashreport = CrashReport.makeCrashReport(var13, "Rendering entity in world");
            throw new ReportedException(crashreport);
        }
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
        GL11.glTranslatef((float) par0, (float) par1, 50.0F);
        GL11.glScalef((float) (-scale), (float) scale, (float) scale);
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
            GL11.glRotatef(-((float) Math.atan((double) (par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        }

        entity.renderYawOffset = hr ? (float) (i ? -1 : 1) * (float) Math.atan((double) (par3 / 40.0F)) * 20.0F : 0.0F;
        entity.rotationYaw = hr ? (float) (i ? -1 : 1) * (float) Math.atan((double) (par3 / 40.0F)) * 40.0F : 0.0F;
        entity.rotationPitch = hr ? -((float) Math.atan((double) (par4 / 40.0F))) * 20.0F : 0.0F;
        entity.rotationYawHead = hr ? entity.rotationYaw : 0.0F;
        entity.prevRotationYawHead = hr ? entity.rotationYaw : 0.0F;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
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

    public static void hairCheck(int par0, int par1, int scale, float par3, float par4, EntityLivingBase par5EntityLivingBase, int side) {
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par0, (float) par1 - (side == 4 ? 20.0F : 0.0F), 50.0F);
        GL11.glScalef((float) (-scale), (float) scale, (float) scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        switch (side) {
            case 1:
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                break;
            case 2:
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                break;
            case 3:
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                break;
            case 4:
                GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
        }

        float f2 = par5EntityLivingBase.renderYawOffset;
        float f3 = par5EntityLivingBase.rotationYaw;
        float f4 = par5EntityLivingBase.rotationPitch;
        float f5 = par5EntityLivingBase.prevRotationYawHead;
        float f6 = par5EntityLivingBase.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        par5EntityLivingBase.renderYawOffset = headRoton ? -((float) Math.atan((double) (par3 / 40.0F))) * 20.0F : 0.0F;
        par5EntityLivingBase.rotationYaw = headRoton ? -((float) Math.atan((double) (par3 / 40.0F))) * 40.0F : (float) headRotX;
        par5EntityLivingBase.rotationPitch = headRoton ? -((float) Math.atan((double) (par4 / 40.0F))) * 20.0F : (float) headRotZ;
        par5EntityLivingBase.rotationYawHead = par5EntityLivingBase.rotationYaw;
        par5EntityLivingBase.prevRotationYawHead = par5EntityLivingBase.rotationYaw;
        GL11.glTranslatef(0.0F, par5EntityLivingBase.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(par5EntityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        par5EntityLivingBase.renderYawOffset = f2;
        par5EntityLivingBase.rotationYaw = f3;
        par5EntityLivingBase.rotationPitch = f4;
        par5EntityLivingBase.prevRotationYawHead = f5;
        par5EntityLivingBase.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void drawLine(int startX, int startY, int endX, int endY, int lineWidth, int color) {
        Tessellator tessellator = Tessellator.instance;

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glColor4ub((byte) (color >> 16 & 255), (byte) (color >> 8 & 255), (byte) (color & 255), (byte) (color >> 24 & 255));

        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        GL11.glScaled(scaledresolution.getScaleFactor(), scaledresolution.getScaleFactor(), 1.0);

        GL11.glLineWidth(lineWidth);

        tessellator.startDrawing(GL11.GL_LINES);
        tessellator.addVertex(startX, startY, 0);
        tessellator.addVertex(endX, endY, 0);
        tessellator.draw();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
    }

    public void current(String var35, int posx, int posy, FontRenderer var8, int var6, int var7) {
        int wid = var8.getStringWidth(var35) / 2;
        int posX = var6 / 2 + posx - wid;
        int posY = var7 / 2 + posy;
        var8.drawString(var35, posX + 1, posY, 0);
        var8.drawString(var35, posX - 1, posY, 0);
        var8.drawString(var35, posX, posY + 1, 0);
        var8.drawString(var35, posX, posY - 1, 0);
        var8.drawString(var35, posX, posY, 8388564);
    }

    public static void dataSend(String c, String d) {
        PD.sendToServer(new JRMCorePData2(c, d));
    }

    public void dri(int id) {
        PD.sendToServer(new DBCPdri(id));
    }

    public static void drawStringWithBorder(FontRenderer fontRendererObj, String text, int x, int y, int color, int borderColor) {
        fontRendererObj.drawString(text, x + 1, y + 2, borderColor);
        fontRendererObj.drawString(text, x - 1, y + 2, borderColor);
        fontRendererObj.drawString(text, x, y + 1 + 2, borderColor);
        fontRendererObj.drawString(text, x, y - 1 + 2, borderColor);
        fontRendererObj.drawString(text, x, y + 2, color);
    }

    public static void drawStringWithBorder(FontRenderer fontRendererObj, String text, int x, int y, int color) {
        drawStringWithBorder(fontRendererObj, text, x, y, color, 0);
    }

    private void updateMajinHairToBodyColor() {
        if (JRMCoreH.isRaceMajin(RaceSlcted) && ColorSlcted != BodyColMainSlcted) {
            ColorSlcted = BodyColMainSlcted;
            setdnsForHair();
        }

    }

    private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_) {
        this.mc.getFramebuffer().unbindFramebuffer();
        GL11.glViewport(0, 0, 256, 256);
        this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.mc.getFramebuffer().bindFramebuffer(true);
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        float f1 = this.width > this.height ? 120.0F / (float) this.width : 120.0F / (float) this.height;
        float f2 = (float) this.height * f1 / 256.0F;
        float f3 = (float) this.width * f1 / 256.0F;
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.width;
        int l = this.height;
        tessellator.addVertexWithUV(0.0D, (double) l, (double) this.zLevel, (double) (0.5F - f2), (double) (0.5F + f3));
        tessellator.addVertexWithUV((double) k, (double) l, (double) this.zLevel, (double) (0.5F - f2), (double) (0.5F - f3));
        tessellator.addVertexWithUV((double) k, 0.0D, (double) this.zLevel, (double) (0.5F + f2), (double) (0.5F - f3));
        tessellator.addVertexWithUV(0.0D, 0.0D, (double) this.zLevel, (double) (0.5F + f2), (double) (0.5F + f3));
        tessellator.draw();
    }

    private void rotateAndBlurSkybox(float p_73968_1_) {
        this.mc.getTextureManager().bindTexture(this.field_110351_G);
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
            tessellator.addVertexWithUV((double) j, (double) k, (double) this.zLevel, (double) (0.0F + f1), 1.0D);
            tessellator.addVertexWithUV((double) j, 0.0D, (double) this.zLevel, (double) (1.0F + f1), 1.0D);
            tessellator.addVertexWithUV(0.0D, 0.0D, (double) this.zLevel, (double) (1.0F + f1), 0.0D);
            tessellator.addVertexWithUV(0.0D, (double) k, (double) this.zLevel, (double) (0.0F + f1), 0.0D);
        }

        tessellator.draw();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColorMask(true, true, true, true);
    }

    private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_) {
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

                this.mc.getTextureManager().bindTexture(titlePanoramaPaths[l]);
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_I(16777215, 255 / (k + 1));
                float f4 = 0.0F;
                tessellator.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double) (0.0F + f4), (double) (0.0F + f4));
                tessellator.addVertexWithUV(1.0D, -1.0D, 1.0D, (double) (1.0F - f4), (double) (0.0F + f4));
                tessellator.addVertexWithUV(1.0D, 1.0D, 1.0D, (double) (1.0F - f4), (double) (1.0F - f4));
                tessellator.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double) (0.0F + f4), (double) (1.0F - f4));
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

    static {
        owner = JRMCoreClient.mc.thePlayer.getCommandSenderName();
        type = JRMCoreH.techBase[3];
        speed = JRMCoreH.techBase[4];
        dam = JRMCoreH.techBase[5];
        effect = JRMCoreH.techBase[6];
        cost = JRMCoreH.techBase[7];
        casttime = JRMCoreH.techBase[8];
        cooldown = JRMCoreH.techBase[9];
        color = JRMCoreH.techBase[10];
        density = JRMCoreH.techBase[11];
        sincantation = JRMCoreH.techBase[12];
        sfire = JRMCoreH.techBase[13];
        smove = JRMCoreH.techBase[14];
        techCrt = new int[]{1, acquired, 1, type, speed, dam, effect, cost, casttime, cooldown, color, density, sincantation, sfire, smove};
        button1 = JRMCoreH.tjjrmc + ":button1.png";
        button2 = JRMCoreH.tjjrmc + ":button2.png";
        guiBG2 = JRMCoreH.tjjrmc + ":gui2.png";
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
        field_110408_a = new ResourceLocation("textures/gui/container/inventory.png");
        count = 0;
        warn = 0;
        startcount = 0;
        scrlld = 0;
    }

    private void nametf(FontRenderer var8, int id, int i, int j) {
        this.inputField2[id].setMaxStringLength(3);
        this.inputField2[id].setEnableBackgroundDrawing(true);
        this.inputField2[id].setCanLoseFocus(true);
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

        dnsH = (String) PresetList.get(HairPrstsSlcted);
    }

    public static void HairCstmPrevPreset() {
        int selct = HairPrstsSlcted - 1;
        if (selct >= 0) {
            HairPrstsSlcted = selct;
        } else {
            HairPrstsSlcted = PresetList.size() - 1;
        }

        dnsH = (String) PresetList.get(HairPrstsSlcted);
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

        for (int selct = 1; selct < JRMCoreH.Races.length; ++selct) {
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

        for (int selct = JRMCoreH.Races.length - 1; selct >= 0; --selct) {
            if (RaceSlcted > selct && JRMCoreH.Allow(JRMCoreH.RaceAllow[selct])) {
                RaceSlcted = selct;
                return;
            }
        }

        for (int var1 = JRMCoreH.Races.length - 1; var1 >= 0; --var1) {
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

            while (loop) {
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

            while (loop) {
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

    private static String ntl(int i) {
        return JRMCoreH.numToLet(i);
    }

    private static String ntl5(int i) {
        return JRMCoreH.numToLet5(i);
    }


    private void updateColorRGB() {
        int r = Integer.parseInt(this.inputField2[0].getText());
        int g = Integer.parseInt(this.inputField2[1].getText());
        int b = Integer.parseInt(this.inputField2[2].getText());

        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));

        int color = (r << 16) + (g << 8) + b;

        switch (colorType) {
            case 4:
                ColorSlcted = color;
                break;
            case 5003:
                BodyColMainSlcted = color;
                break;
            case 5004:
                BodyColSub1Slcted = color;
                break;
            case 5005:
                BodyColSub2Slcted = color;
                break;
            case 5009:
                EyeCol1Slcted = color;
                break;
            case 5010:
                EyeCol2Slcted = color;
                break;
            case 5014:
                BodyColSub3Slcted = color;
                break;
            case 5015:
                KiColorSlcted = color;
                break;
            case 5016:
                BodyauColMainSlcted = color;
                setdnsau();
                JRMCoreH.jrmcDataFC(3, dnsau);
                break;
            case 5017:
                BodyauColSub1Slcted = color;
                setdnsau();
                JRMCoreH.jrmcDataFC(3, dnsau);
                break;
            case 5018:
                BodyauColSub2Slcted = color;
                setdnsau();
                JRMCoreH.jrmcDataFC(3, dnsau);
                break;
            case 5019:
                BodyauColSub3Slcted = color;
                setdnsau();
                JRMCoreH.jrmcDataFC(3, dnsau);
                break;
        }

        if (this.guiIDprev == 8) {
            setdnsForHair();
        } else {
            setdns();
        }

        this.updateMajinHairToBodyColor();

    }
}

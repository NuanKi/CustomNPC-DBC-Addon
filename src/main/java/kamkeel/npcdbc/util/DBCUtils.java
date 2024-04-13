package kamkeel.npcdbc.util;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.JRMCore.ComJrmcaBonus;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import kamkeel.npcdbc.api.IDBCStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity.EnumEntitySize;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static JinRyuu.JRMCore.JRMCoreH.*;

// Created by Goatee
public class DBCUtils {
    public static String[][] fullFormNames = new String[][]{{"Base", "Full Power", "Buffed State", "God Ki State"}, {"§8Base", "Super Saiyan", "Super Saiyan (G2)", "Super Saiyan (G3)", "Super Saiyan", "Super Saiyan 2", "Super Saiyan 3", "Great Ape", "Super Great Ape", "Super Saiyan God", "Super Saiyan Blue", "", "", "", "Super Saiyan 4", "SS Blue (Evolved)"}, {"§8Base", "Super Saiyan", "Super Saiyan (G2)", "Super Saiyan (G3)", "Super Saiyan", "Super Saiyan 2", "Super Saiyan 3", "Great Ape", "Super Great Ape", "Super Saiyan God", "Super Saiyan Blue", "", "", "", "Super Saiyan 4", "SS Blue (Evolved)"}, {"Base", "Full Power Release", "Giant Form", "God Ki State"}, {"First Form", "First Form (Buffed)", "Second Form", "Third Form", "Final Form", "Ultimate Form", "Golden Form", "God Ki State"}, {"Base", "Evil Form", "Full Power (Buffed)", "Purest Form", "God Ki State"}};
    public static String[][] shortFormNames = new String[][]{{}, {"§8Base", "SS", "SS (G2)", "SS (G3)", "SS", "SS 2", "SS 3", "Great Ape", "Super Great Ape", "SS God", "SS Blue", "", "", "", "SS 4", "SSB (Evo)"}};
    public static int[][] formColors = new int[][]{{0, 0xFFEF99, 0xFFEF99, 0xFFEF99, 0xFFEF99, 0xFFEF99, 0xFFEF99, 0x964B00, 0xFFEF99, 0xFD4345, 0x46D2F5, 0, 0, 0, 0xC11D00, 0x2039A0}}; // ssg
    public static float height;
    public static float width;

    public static String[] formLabels = new String[]{"Mystic", "Legendary", "Divine", "Rosé", "(Mastered)", "Ritual", "Ultra Instinct", "Mastered UI", "God Of Destruction"};

    public static String getFullState1Name(int race, int state, boolean divine, boolean l, boolean ui, boolean w) {
        String fn = fullFormNames[race][state];
        String u = " (" + (w ? "M" : "") + "UI)";
        String u2 = " " + (w ? "M" : "") + "UI";

        if (ui && state != 0 && !divine) {
            if (state == 7 || state == 8)
                return "";

            if (state == 14)
                fn = fullFormNames[race][state] + u2;
            else if (JRMCoreH.rc_sai())
                fn = shortFormNames[race][state] + u;

        } else {
            if (l && canBeLgnd(race, state))
                if (state == 8)
                    fn = "Ape";
                else
                    fn = shortFormNames[race][state];
            if (divine && JRMCoreH.rc_sai(race)) {
                if (state == 10)
                    if (ui)
                        fn = "SS Rosé" + u;
                    else
                        fn = "Super Saiyan Rosé";
                if (state == 15)
                    if (ui)
                        fn = "SS Rosé Evo" + u;
                    else
                        fn = "SS Rosé (Evolved)";
                if (state == 14 && !ui)
                    fn = "Divine SS 4";

            }
        }

        if (JRMCoreH.StusEfctsMe(20) || JRMCoreH.StusEfctsMe(13) || ui && state == 0)
            fn = "";

        // JRMCoreH.getFormMaster

        return fn;
    }

    public static int getCurrentFormColor() {
        int race = JRMCoreH.Race;
        int state = JRMCoreH.State;
        boolean l = JRMCoreH.StusEfctsMe(14);
        boolean divine = JRMCoreH.StusEfctsMe(17);
        boolean mystic = JRMCoreH.StusEfctsMe(13);
        boolean ui = JRMCoreH.StusEfctsMe(19);
        boolean uiHairWhite = DBCUtils.isUIWhite(ui, JRMCoreH.State2);
        boolean GoD = JRMCoreH.StusEfctsMe(20);
        boolean kk = JRMCoreH.StusEfctsMe(5);
        int c = 0;
        if (JRMCoreH.rc_sai())
            c = formColors[0][state];
        //else
        //c = GuiTKeys.getFormColorState(state);
        if (l && canBeLgnd(race, state))
            c = 0x55FF55;
        if (divine)
            if (JRMCoreH.rc_sai()) {
                if (state == 10)
                    c = 0xFF9BD5;
                else if (state == 15)
                    c = 0xFF4284;// 0xD86FA0;
                else if (state == 14)
                    c = 0xFF6684;
            }
        if (mystic)
            c = 0xE7EACE;
        if (ui)
            if (uiHairWhite)
                c = 0xE8E8E8;// 0xC1C1C1;//0xF0F0F0;
            else
                c = 0x6A6D70;
        if (GoD)
            c = 0xAA00AA;
        if (kk)
            c = 0xFF5555;
        return c;

    }

    public static String getFullState2Name(int race, int state, boolean l, boolean divine, boolean mystic, boolean ui, boolean uiHairWhite, boolean GoD) {
        String st2 = "";
        if (l && canBeLgnd(race, state) && !divine && !ui)
            st2 = formLabels[1];
        if (mystic)
            st2 = formLabels[0];
        if (ui && (state == 0 || state == 7 || state == 8))
            if (uiHairWhite)
                st2 = formLabels[7];
            else
                st2 = formLabels[6];

        if (GoD)
            st2 = formLabels[8];
        boolean kk = JRMCoreH.StusEfctsMe(5);
        if (kk)
            st2 = "Kaioken " + JRMCoreH.TransKaiNms[JRMCoreH.State2];

        return st2;
    }

    public static String getCurrentFormFullName(int race, int state, boolean l, boolean divine, boolean mystic, boolean ui, boolean uiHairWhite, boolean GoD) {
        String st1 = getFullState1Name(race, state, divine, l, ui, uiHairWhite);
        String st2 = getFullState2Name(race, state, l, divine, mystic, ui, uiHairWhite, GoD);

        st1 = st1.equals("§8Base") && !st2.equals("") ? "" : st1;

        String name = st2.equals("") ? st1 : st2 + " " + st1;

        return name;
    }

    public static Boolean canBeLgnd(int race, int st) {
        if (race == 1 | race == 2)
            if (st != 0 && st != 9 && st != 10 && st != 15 && st != 7)
                return true;
            else
                return false;
        else
            return false;
    }

    public static int getEyeColor(int t, int d, int p, int r, int s, boolean v, boolean y, boolean ui, boolean ui2, boolean gd) {
        if (ui2 || ui)
            return t == 15790320 ? 15790320 : (t == 1 ? 13816530 : 15790320);
        // if (!ssb && !ssbs && !ss4 && !ssg && !ss)
        return JRMCoreHDBC.getPlayerColor(t, d, p, r, s, v, y, ui, false, gd);
    }

    public static Boolean isUIWhite(boolean ui, int st2) {
        if (!ui)
            return false;
        int i = JGConfigUltraInstinct.CONFIG_UI_LEVELS < st2 ? JGConfigUltraInstinct.CONFIG_UI_LEVELS : st2;
        int ultra_instinct_level = JRMCoreH.state2UltraInstinct(false, (byte) i);
        return JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[ultra_instinct_level];
    }

    public static int lastUIlvl(boolean hairWhite, EntityPlayer p) {
        int ui = JRMCoreH.SklLvl(16, p);
        int curLvl = JGConfigUltraInstinct.CONFIG_UI_LEVELS < ui ? JGConfigUltraInstinct.CONFIG_UI_LEVELS : ui;
        boolean[] haircol = JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE;
        int blacklevel = 0, whitelevel = 0;
        for (int i = 0; i < curLvl; i++)
            if (!haircol[i])
                blacklevel = i + 1;
            else
                whitelevel = i + 1;
        if (hairWhite)
            return whitelevel;
        else
            return blacklevel;

    }

    public static boolean hasMUI(EntityPlayer p) {
        return lastUIlvl(true, p) > 0;
    }

    public static boolean isMUI(EntityPlayer p) {
        int ui = JRMCoreH.SklLvl(16, p);
        int curLvl = JGConfigUltraInstinct.CONFIG_UI_LEVELS < ui ? JGConfigUltraInstinct.CONFIG_UI_LEVELS : ui;
        boolean[] haircol = JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE;
        boolean is = false;
        for (int i = 0; i < curLvl; i++) {
            if (haircol[i])
                if (i + 1 == JRMCoreH.State2)
                    is = true;
        }

        return is;
    }

    public static int getFullAttribute(EntityPlayer p, int attribute) {
        boolean x = p.worldObj.isRemote;
        int powerType = x ? JRMCoreH.Pwrtyp : JRMCoreH.getByte(p, "jrmcPwrtyp");
        int race = x ? JRMCoreH.Race : JRMCoreH.getByte(p, "jrmcRace");
        int state = x ? JRMCoreH.State : JRMCoreH.getByte(p, "jrmcState");
        int state2 = x ? JRMCoreH.State2 : JRMCoreH.getByte(p, "jrmcState2");
        double release = x ? JRMCoreH.curRelease : JRMCoreH.getByte(p, "jrmcRelease");
        String sklx = x ? JRMCoreH.PlyrSkillX : JRMCoreH.getString(p, "jrmcSSltX");
        int resrv = x ? JRMCoreH.getArcRsrv() : JRMCoreH.getInt(p, "jrmcArcRsrv");
        String absorption = x ? JRMCoreH.getMajinAbsorption() : JRMCoreH.getString(p, "jrmcMajinAbsorptionData");
        String statusEffects = getStE(p);
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
        String[] PlyrSkills = JRMCoreH.PlyrSkills(p);
        boolean mj = JRMCoreH.StusEfcts(12, statusEffects);
        boolean c = (JRMCoreH.StusEfcts(10, statusEffects) || JRMCoreH.StusEfcts(11, statusEffects));
        boolean lg = JRMCoreH.StusEfcts(14, statusEffects);
        boolean kk = JRMCoreH.StusEfcts(5, statusEffects);
        boolean mc = JRMCoreH.StusEfcts(13, statusEffects);
        boolean mn = JRMCoreH.StusEfcts(19, statusEffects);
        boolean gd = JRMCoreH.StusEfcts(20, statusEffects);
        return JRMCoreH.getPlayerAttribute(p, PlyrAttrbts, attribute, state, state2, race, sklx, (int) release, resrv, lg, mj, kk, mc, mn, gd, powerType, PlyrSkills, c, absorption);
    }


    public static int[] getAllFullAttributes(EntityPlayer p) {
        boolean x = p.worldObj.isRemote;
        int powerType = x ? JRMCoreH.Pwrtyp : JRMCoreH.getByte(p, "jrmcPwrtyp");
        int race = x ? JRMCoreH.Race : JRMCoreH.getByte(p, "jrmcRace");
        int state = x ? JRMCoreH.State : JRMCoreH.getByte(p, "jrmcState");
        int state2 = x ? JRMCoreH.State2 : JRMCoreH.getByte(p, "jrmcState2");
        double release = x ? JRMCoreH.curRelease : JRMCoreH.getByte(p, "jrmcRelease");
        String sklx = x ? JRMCoreH.PlyrSkillX : JRMCoreH.getString(p, "jrmcSSltX");
        int resrv = x ? JRMCoreH.getArcRsrv() : JRMCoreH.getInt(p, "jrmcArcRsrv");
        String absorption = x ? JRMCoreH.getMajinAbsorption() : JRMCoreH.getString(p, "jrmcMajinAbsorptionData");
        String statusEffects = getStE(p);
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
        String[] PlyrSkills = JRMCoreH.PlyrSkills(p);
        boolean mj = JRMCoreH.StusEfcts(12, statusEffects);
        boolean c = (JRMCoreH.StusEfcts(10, statusEffects) || JRMCoreH.StusEfcts(11, statusEffects));
        boolean lg = JRMCoreH.StusEfcts(14, statusEffects);
        boolean kk = JRMCoreH.StusEfcts(5, statusEffects);
        boolean mc = JRMCoreH.StusEfcts(13, statusEffects);
        boolean mn = JRMCoreH.StusEfcts(19, statusEffects);
        boolean gd = JRMCoreH.StusEfcts(20, statusEffects);
        int[] a = new int[6];
        for (int i = 0; i <= 5; i++)
            a[i] = JRMCoreH.getPlayerAttribute(p, PlyrAttrbts, i, state, state2, race, sklx, (int) release, resrv, lg, mj, kk, mc, mn, gd, powerType, PlyrSkills, c, absorption);

        return a;
    }

    public static int getMaxStat(EntityPlayer p, int att) { // gets max player stat, 0 dmg 1 def only, rest are
        boolean x = p.worldObj.isRemote;
        int race = x ? JRMCoreH.Race : JRMCoreH.getByte(p, "jrmcRace");
        int powerType = x ? JRMCoreH.Pwrtyp : JRMCoreH.getByte(p, "jrmcPwrtyp");
        byte classID = x ? JRMCoreH.Class : JRMCoreH.getByte(p, "jrmcClass");
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
        int[] PlyrAttrbtsFull = getAllFullAttributes(p);

        for (int i = 0; i < PlyrAttrbts.length; i++) {
            if (i == 0 || i == 1 || i == 4)
                PlyrAttrbts[i] = PlyrAttrbtsFull[i];
        }

        float f = att == 5 ? JRMCoreH.SklLvl_KiBs(p, 1) : 0f;
        int stat = JRMCoreH.stat(p, att, powerType, att, PlyrAttrbts[att], race, classID, f);

        if (att == 0)
            stat += getExtraOutput(p, att, 100);
        else if (att == 1)
            stat += getExtraOutput(p, att, 100);

        return stat;
    }

    public static int getCurrentStat(EntityPlayer p, int attribute) { // gets stat at current release
        int stat = getMaxStat(p, attribute);
        int release = JRMCoreH.getByte(p, "jrmcRelease");
        double curAtr = (stat) * (release * 0.01D) * JRMCoreH.weightPerc(0, p);
        return (int) (curAtr);
    }

    public static int getExtraOutput(EntityPlayer p, int att, int release) {
        int extraoutput = 0;
        if (att == 0) {
            int maxki = getMaxStat(p, 5);
            extraoutput = (int) (JRMCoreH.SklLvl(12, p) * 0.0025 * maxki * release * 0.01);
        } else if (att == 1) {
            int maxki = getMaxStat(p, 5);
            extraoutput = (int) (JRMCoreH.SklLvl(11, p) * 0.005 * maxki * release * 0.01);
        } else if (att == 5) {
            extraoutput = getMaxStat(p, 5) - JRMCoreH.stat(p, att, JRMCoreH.getByte(p, "jrmcPwrtyp"), att, JRMCoreH.PlyrAttrbts(p)[5], JRMCoreH.getByte(p, "jrmcRace"), JRMCoreH.getByte(p, "jrmcClass"), 0);
        }
        return extraoutput;
    }

    public static void setDmgRed(Entity entity, float dmgred) {
        if (entity != null)
            if (entity instanceof EntityPlayer)
                JRMCoreH.setFloat(dmgred, (EntityPlayer) entity, "dmgred");
            else
                JRMCoreH.nbt(entity).setFloat("dmgred", dmgred);

    }

    public static float getDmgRed(Entity entity) {
        if (entity != null)
            if (entity instanceof EntityPlayer)
                return JRMCoreH.getFloat((EntityPlayer) entity, "dmgred");
            else
                return JRMCoreH.nbt(entity).getFloat("dmgred");
        return 0;
    }

    public static void kiCost(EntityPlayer p, int kiToDrain) {
        int ki = JRMCoreH.getInt(p, "jrmcEnrgy");
        int newKi = ki - kiToDrain;
        JRMCoreH.setInt(newKi < 0 ? 0 : newKi, p, "jrmcEnrgy");
    }

    public static void kiCostAsPercentOfMax(EntityPlayer p, float percToDrain) {
        int maxKi = getMaxStat(p, 5);
        int newKi = (int) (maxKi * (percToDrain / 100));
        kiCost(p, newKi);
    }

    public static boolean hasKiAsPercentOfMax(EntityPlayer p, float perc) {
        int ki = JRMCoreH.getInt(p, "jrmcEnrgy");
        int maxKi = getMaxStat(p, 5);
        int newKi = (int) (maxKi * (perc / 100));
        return ki >= newKi;
    }

    public static double getCurFormMulti(EntityPlayer p) {
        double str = JRMCoreH.PlyrAttrbts(p)[0];
        double maxstr = getFullAttribute(p, 0);
        return maxstr / str;

    }

    public static float bodyPerc(EntityPlayer p) {
        float curBody = !p.worldObj.isRemote ? JRMCoreH.getInt(p, "jrmcBdy") : JRMCoreH.curBody;
        float maxBody = getMaxStat(p, 2);

        return curBody * 100 / maxBody;

    }

    public static String getData(int id, EntityPlayer p) {
        for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
            if (JRMCoreH.plyrs[pl].equals(p.getCommandSenderName())) {
                return JRMCoreH.data(id)[pl];
            }
        }
        return "";

    }

    public static double getMaxFormMasteryLvl(int st, int race) {
        // int n = JRMCoreH.trans[JRMCoreH.Race].length - 1; // kk? n + 1 : mys? n + 2 :
        // ui? n + 3: god? n + 4 : n;
        String max = JGConfigDBCFormMastery.getString(race, st, JGConfigDBCFormMastery.DATA_ID_MAX_LEVEL, 0);
        return Double.parseDouble(max);
    }

    public static double getFormMasteryValue(EntityPlayer p, int race, String formName) {
        String fm = JRMCoreH.getFormMasteryData(p);
        String masteries[] = fm.split(";");

        for (String s : masteries)
            if (s.toLowerCase().contains(formName.toLowerCase())) {
                String[] masteryvalues = s.split(",");
                double masteryvalue = Double.parseDouble(masteryvalues[1]);
                return masteryvalue;
            }

        return -1;
    }

    public static boolean isFM(EntityPlayer p, String formName, int race, double perc) {
        int st = JRMCoreH.getFormID(formName, race, true);
        double max = getMaxFormMasteryLvl(st, race);
        double fm = getFormMasteryValue(p, race, formName);
        return fm >= perc(max, perc);
    }

    public static double perc(double n, double perc) {
        return n / 100 * perc;
    }

    public static boolean isFMMax(EntityPlayer p, String formName, int race) {
        return isFM(p, formName, race, 100);
    }

    public static String getStE(EntityPlayer p) {
        boolean x = p.worldObj.isRemote;
        return x ? JRMCoreH.StusEfctsMe() : JRMCoreH.getString(p, "jrmcStatusEff");
    }

    public static int getMeleeDamage(EntityPlayer attacker) { // without extra output and passive output
        return (int) getCurrentStat(attacker, 0);
    }

    public static void sS(EntityPlayer p, float par1, float par2) {
        if (par1 != p.width || par2 != p.height) {
            p.width = par1;
            p.height = par2;
            p.boundingBox.maxX = p.boundingBox.minX + (double) p.width;
            p.boundingBox.maxZ = p.boundingBox.minZ + (double) p.width;
            p.boundingBox.maxY = p.boundingBox.minY + (double) p.height;
        }

        float f2 = par1 % 2.0F;
        if ((double) f2 < 0.375) {
            p.myEntitySize = EnumEntitySize.SIZE_1;
        } else if ((double) f2 < 0.75) {
            p.myEntitySize = EnumEntitySize.SIZE_2;
        } else if ((double) f2 < 1.0) {
            p.myEntitySize = EnumEntitySize.SIZE_3;
        } else if ((double) f2 < 1.375) {
            p.myEntitySize = EnumEntitySize.SIZE_4;
        } else if ((double) f2 < 1.75) {
            p.myEntitySize = EnumEntitySize.SIZE_5;
        } else {
            p.myEntitySize = EnumEntitySize.SIZE_6;
        }
    }

    public static int getPlayerID(EntityPlayer p) {
        if (p.worldObj.isRemote) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++)
                if (JRMCoreH.plyrs[pl].equals(p.getCommandSenderName()))
                    return pl;
        }
        return 0;
    }

    public static String[] getAllBonuses(EntityPlayer p) {
        ArrayList<String> results = new ArrayList<>();

        String d31 = getBonusAttString(p);

        String[] attributeTags;
        ArrayList<String> tagslist = new ArrayList<>();
        if (d31 != null) {
            if (d31.contains("=")) {
                attributeTags = d31.split("=");
                tagslist = new ArrayList<>(Arrays.asList(attributeTags));
            } else
                tagslist.add(d31);

            for (String attributeTag : tagslist) {
                if (attributeTag.contains("|")) {
                    String[] split = attributeTag.split("\\|");
                    Map<Character, Integer> numOperatorPairs = new HashMap<>();

                    for (String str : split) {
                        String[] b = str.split(";");
                        char operation = b[1].charAt(0);
                        int num = Integer.parseInt(b[1].substring(1));

                        if (numOperatorPairs.containsKey(operation))
                            numOperatorPairs.replace(operation, numOperatorPairs.get(operation) + num);
                        else
                            numOperatorPairs.put(operation, num);

                    }

                    StringBuilder output = new StringBuilder();
                    for (Map.Entry<Character, Integer> entry : numOperatorPairs.entrySet()) {
                        char operation = entry.getKey();
                        int num = entry.getValue();
                        output.append(operation).append(num).append(",");
                    }
                    if (output.length() > 0)
                        output.deleteCharAt(output.length() - 1);
                    if (output.length() == 0)
                        output = new StringBuilder("0");
                    results.add(output.toString());
                } else if (!attributeTag.equals("n")) {
                    String[] split = attributeTag.split(";");
                    char operation = split[1].charAt(0);
                    int num = Integer.parseInt(split[1].substring(1));

                    results.add(operation + Integer.toString(num));
                } else
                    results.add("0");
            }
        }
        String[] r = results.toArray(new String[6]);
        for (int i = 0; i < r.length; i++) {
            if (r[i] == null)
                r[i] = "0";
        }
        return r;

    }

    public static float bonusMulti(EntityPlayer p, int stat) {
        float multi = 1.0F;
        String s = getAllBonuses(p)[stat];
        if (s.equals("0"))
            s = "1";
        ArrayList<String> values = new ArrayList<>();
        if (s.contains(","))
            values = new ArrayList<>(Arrays.asList(s.split(",")));
        else
            values.add(s);
        for (String b : values) {
            char operation = b.charAt(0);
            if (operation == '*')
                multi = Float.parseFloat(b.substring(1));

        }
        return multi;
    }

    public static float bonusDiv(EntityPlayer p, int stat) {
        float multi = 1.0F;
        String s = getAllBonuses(p)[stat] != null ? getAllBonuses(p)[stat] : "1";
        if (s.equals("0"))
            s = "1";
        ArrayList<String> values = new ArrayList<>();
        if (s.contains(","))
            values = new ArrayList<>(Arrays.asList(s.split(",")));
        else
            values.add(s);
        for (String b : values) {
            char operation = b.charAt(0);
            if (operation == '/')
                multi = Float.parseFloat(b.substring(1));

        }
        return multi;
    }

    public static int bonusAdd(EntityPlayer p, int stat) {
        int multi = 0;
        String s = getAllBonuses(p)[stat];

        ArrayList<String> values = new ArrayList<>();
        if (s.contains(","))
            values = new ArrayList<>(Arrays.asList(s.split(",")));
        else
            values.add(s);
        for (String b : values) {
            char operation = b.charAt(0);
            if (operation == '+')
                multi = Integer.parseInt(b.substring(1));

        }
        return multi;
    }

    public static int bonusSub(EntityPlayer p, int stat) {
        int multi = 0;
        String s = getAllBonuses(p)[stat];

        ArrayList<String> values = new ArrayList<>();
        if (s.contains(","))
            values = new ArrayList<>(Arrays.asList(s.split(",")));
        else
            values.add(s);
        for (String b : values) {
            char operation = b.charAt(0);
            if (operation == '-')
                multi = Integer.parseInt(b.substring(1));

        }
        return multi;
    }

    public static String getBonusAttString(EntityPlayer p) {
        String s = "";
        if (!p.worldObj.isRemote) {
            NBTTagCompound nbt = JRMCoreH.nbt(p, "pres");
            for (int i = 0; i <= 5; i++) {
                String a = nbt.getString("jrmcAttrBonus" + ComJrmcaBonus.ATTRIBUTES_SHORT[i]);
                if (!a.isEmpty())
                    s += a + "=";
            }
        } else
            s = JRMCoreH.dat31[getPlayerID(p)];
        s = s.isEmpty() ? null : s;
        return s;
    }

    public static int[] statsWithBonus(EntityPlayer p) {

        int[] oldstats = getAllFullAttributes(p);
        if (!JRMCoreConfig.JRMCABonusOn || getBonusAttString(p) == null)
            return oldstats;
        int[] newstats = new int[6];

        for (int i = 0; i < oldstats.length; i++) {
            int o = oldstats[i];
            newstats[i] = (int) ((o / bonusDiv(p, i) * bonusMulti(p, i)) + bonusAdd(p, i) - bonusSub(p, i));
        }
        return newstats;
    }

    public static void knockback(EntityLivingBase targetEntity, Entity attacker, int knockbackStrength) {
        if (knockbackStrength > 0) {

            float var25 = MathHelper.sqrt_double(attacker.motionX * attacker.motionX + attacker.motionZ * attacker.motionZ);

            if (var25 > 0.0F) {
                targetEntity.addVelocity(attacker.motionX * knockbackStrength * 0.6000000238418579D / var25, 0.1D, attacker.motionZ * knockbackStrength * 0.6000000238418579D / var25);
            }
        }
    }

    public static int doDBCDamage(EntityPlayer player, int damageAmount, IDBCStats dbcStats) {
        if (!player.worldObj.isRemote && dbcStats != null && damageAmount > 0) {
            if (!player.capabilities.isCreativeMode) {
                ExtendedPlayer props = ExtendedPlayer.get(player);
                boolean block = props.getBlocking() == 1;

                int[] attributes = PlyrAttrbts(player);
                String[] playerSkills = PlyrSkills(player);

                NBTTagCompound nbt = nbt(player, "pres");
                byte state = nbt.getByte("jrmcState");
                byte state2 = nbt.getByte("jrmcState2");
                String racialSkill = getString(player, "jrmcSSltX");
                byte race = nbt.getByte("jrmcRace");
                byte powerType = nbt.getByte("jrmcPwrtyp");
                byte classID = nbt.getByte("jrmcClass");
                byte release = getByte(player, "jrmcRelease");
                int arcoPP = getInt(player, "jrmcArcRsrv");
                String absorption = getString(player, "jrmcMajinAbsorptionData");
                int currStamina = getInt(player, "jrmcStamina");
                int currEnergy = getInt(player, "jrmcEnrgy");

                String statusEffects = getString(player, "jrmcStatusEff");
                boolean isMajin = StusEfcts(12, statusEffects);
                boolean isMystic = StusEfcts(13, statusEffects);
                boolean isLegendary = StusEfcts(14, statusEffects);
                boolean isKK = StusEfcts(5, statusEffects);
                boolean isUI = StusEfcts(19, statusEffects);
                boolean isGoD = StusEfcts(20, statusEffects);
                boolean isFused = JRMCoreH.StusEfcts(10, statusEffects) || JRMCoreH.StusEfcts(11, statusEffects);

                int DEX = 0;
                int CON = attributes[2];

                // Dex Calculation
                if (!dbcStats.isIgnoreDex())
                    DEX = getPlayerAttribute(player, attributes, 1, state, state2, race, racialSkill, release, arcoPP, isLegendary, isMajin, isKK, isMystic, isUI, isGoD, powerType, playerSkills, isFused, absorption);

                int def = 0;
                // KI STUFF
                boolean kiProtectOn = !PlyrSettingsB((EntityPlayer) player, 10);
                int kiProtection = 0;
                int kiProtectionCost = 0;
                int maxKiPool = JRMCoreH.stat(player, 5, powerType, 5, attributes[5], race, classID, JRMCoreH.SklLvl_KiBs(playerSkills, powerType));
                int kiProtectLevel = JRMCoreH.SklLvl(11, playerSkills);
                ;

                double formDamageReduction = 1;
                if (!dbcStats.isIgnoreFormReduction()) {
                    int formDecimal = getPlayerAttribute(player, attributes, 2, state, state2, race, racialSkill, release, arcoPP, isLegendary, isMajin, isKK, isMystic, isUI, isGoD, powerType, playerSkills, isFused, absorption);
                    formDamageReduction = (double) (Math.max(formDecimal, CON)) / ((double) CON);
                }

                def = stat(player, 1, powerType, 1, DEX, race, classID, 0.0F);
                def = (int) ((double) def * (double) release * 0.01 * (double) weightPerc(1, player));

                if (kiProtectOn) {
                    ////////////////////
                    //// IF KI PROTECTION IGNORE
                    // kiProtection = 0
                    if (!dbcStats.isIgnoreKiProtection()) {
                        kiProtection = (int) ((double) kiProtectLevel * 0.005 * maxKiPool * (double) release * 0.01);
                        if (kiProtection < 1) {
                            kiProtection = 1;
                        }

                        // Ki Protection
                        kiProtection = (int) ((double) kiProtection * DBCConfig.cnfKDd);
                        float kiProtectDamage = (float) damageAmount / 3.0F / (float) (damageAmount + "").length();
                        if (kiProtectDamage < 1.0F) {
                            kiProtectDamage = 1.0F;
                        }
                        ////////////////////

                        ////////////////////
                        // Cost Ki Protection
                        kiProtectionCost = (int) ((double) kiProtectLevel * (double) release * 0.01 * (double) kiProtectDamage);
                        if (kiProtectionCost < 1) {
                            kiProtectionCost = 1;
                        }
                        kiProtectionCost = (int) ((double) kiProtectionCost * DBCConfig.cnfKDc);
                        ////////////////////
                    }
                }
                def += kiProtection;


                int staminaCost = (int) ((float) (def - kiProtection) * 0.05F);
                ////////////////////
                ////// EFFECT STAMINA BOOL
                // Reduce Stamina
                if (block && !dbcStats.isIgnoreBlock() && currStamina >= staminaCost) {
                    if (!isInCreativeMode(player)) {
                        setInt(Math.max(currStamina - staminaCost, 0), player, "jrmcStamina");
                    }
                } else {
                    // Passive Dex
                    def = (int) ((float) ((def - kiProtection) * JRMCoreConfig.StatPasDef) * 0.01F) + kiProtection;
                }
                ////////////////////

                ////////////////////
                ////// EFFECT KI BOOL
                // Reduce Energy
                if (currEnergy >= kiProtectionCost) {
                    if (!isInCreativeMode(player)) {
                        setInt(Math.max(currEnergy - kiProtectionCost, 0), player, "jrmcEnrgy");
                    }
                } else {
                    def -= kiProtection;
                }
                ////////////////////

                // Damage Amount before any calculations modify it
                int rawDamage = damageAmount;

                // Defense after Ki Protection / Dex calculations
                int rawDefense = def;

                double enduranceReduction = 1;
                if (!dbcStats.isIgnoreEndurance()) {
                    int enduranceLevel = SklLvl(4, player);

                    // Calculates the Amount to Reduce the Damage with Endurance Level
                    enduranceReduction = (double) (1.0F - 0.03F * (float) enduranceLevel);
                }

                double damageBreakThrough = damageAmount;
                // Default Penetration of the NPC
                // By default all entities are 10. Players have their own
                // default penetration skill. Based on if they are in Legendary
                int npcDefensePenetration = dbcStats.getDefensePenetration();
                if (dbcStats.hasDefensePenetration()) {
                    // Defense Penetrated = RawDefense * (defensePen * 0.01%)
                    // Defense Pen of 10 --> RawDefense * 0.1 -- 10% Penetrated
                    // Defense Pen of 50 --> RawDefense * 0.5 -- 50% Penetrated
                    // Defense Pen of 100 --> RawDefense * 1 -- 100% Penetrated
                    int defensePenetrated = (int) ((float) (rawDefense * npcDefensePenetration) * 0.01F);

                    // The Amount of Damage that will break through based on defensePenetrated
                    damageBreakThrough = (double) (damageAmount - (rawDefense - defensePenetrated));
                }

                // Damage after Reduction
                damageAmount = (int) (damageBreakThrough * enduranceReduction);

                // Prevents Negative Damages
                damageAmount = Math.max(damageAmount, 1);

                if (dbcStats.hasDefensePenetration()) {
                    // Guarantee Damage is dealt with Defense Penetration
                    if ((double) ((float) (rawDamage * npcDefensePenetration) * 0.01F) * enduranceReduction > (double) damageAmount) {
                        damageAmount = (int) ((double) ((float) (rawDamage * npcDefensePenetration) * 0.01F) * enduranceReduction);
                    }
                }

                // Consider Stamina Cost or Con on the Damage Amount
                damageAmount = (int) ((double) damageAmount / formDamageReduction);

                int playerHP = getInt(player, "jrmcBdy");
                int reducedHP = playerHP - damageAmount;
                int newHP = Math.max(reducedHP, 0);

                boolean friendlyFist = dbcStats.isFriendlyFist();
                if (friendlyFist) {
                    int ko = getInt(player, "jrmcHar4va");
                    newHP = Math.max(reducedHP, 20);
                    if (ko <= 0 && newHP == 20) {
                        setInt((int) 6, player, "jrmcHar4va");
                        setByte(race == 4 ? (state < 4 ? state : 4) : 0, player, "jrmcState");
                        setByte((int) 0, player, "jrmcState2");
                        setByte((int) 0, player, "jrmcRelease");
                        setInt((int) 0, player, "jrmcStamina");
                        StusEfcts(19, statusEffects, (EntityPlayer) player, false);
                    }
                    damageAmount -= reducedHP;
                }

                if (!isInCreativeMode(player)) {
                    setInt(newHP, player, "jrmcBdy");
                }
            }
        }
        return damageAmount;
    }
}
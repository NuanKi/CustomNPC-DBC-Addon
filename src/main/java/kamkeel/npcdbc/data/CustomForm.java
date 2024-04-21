package kamkeel.npcdbc.data;

import kamkeel.npcdbc.CustomNpcPlusDBC;
import kamkeel.npcdbc.api.ICustomForm;
import kamkeel.npcdbc.api.IFormMastery;
import kamkeel.npcdbc.constants.DBCForm;
import kamkeel.npcdbc.constants.DBCRace;
import kamkeel.npcdbc.controllers.FormController;
import kamkeel.npcdbc.util.Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;

public class CustomForm implements ICustomForm {

    public int id = -1; // Only for internal usage
    public String name = "";
    //name to be displayed in DBC GUI after "Form: ", preferably short as space is narrow
    public String menuName = "§2§lCustom Form";
    public int race = DBCRace.ALL;

    public boolean hasSize = false;
    public float formSize = 1.0f;

    public float strengthMulti = 1.0f;
    public float dexMulti = 1.0f;
    public float willMulti = 1.0f;

    public boolean kaiokenStackable = true, uiStackable = true, godStackable = true, mysticStackable = true;
    public float kaiokenStrength = 1.0f, uiStrength = 1.0f, godStrength = 1.0f, mysticStrength = 1.0f;
    public float kaiokenState2Factor = 1.0f, uiState2Factor = 1.0f;

    public String hairCode = "", hairType = "", bodyType = "";
    public int auraColor = -1, hairColor = -1, eyeColor = -1, bodyCM = -1, bodyC1 = -1, bodyC2 = -1, bodyC3 = -1, furColor = -1;

    public String ascendSound = "jinryuudragonbc:1610.sss", descendSound = CustomNpcPlusDBC.ID + ":transformationSounds.GodDescend";


    public FormMastery formMastery = new FormMastery(this);
    /**
     * ID of parent and child forms of this
     */
    public int childID = -1, parentID = -1;


    public CustomForm() {
    }

    public CustomForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getHairCode() {
        return hairCode;
    }

    @Override
    public void setHairCode(String hairCode) {
        this.hairCode = hairCode;
    }

    @Override
    public boolean hasColor(String type) {
        switch (type.toLowerCase()) {
            case "aura":
                return auraColor != -1;
            case "hair":
                return hairColor != -1;
            case "eye":
                return eyeColor != -1;
            case "bodycm":
                return bodyCM != -1;
            case "bodyc1":
                return bodyC1 != -1;
            case "bodyc2":
                return bodyC2 != -1;
            case "bodyc3":
                return bodyC3 != -1;
            case "fur":
                return furColor != -1;

        }
        throw new CustomNPCsException("Invalid type! Legal types: aura, hair, eye, bodycm, bodyc1, bodyc2, bodyc3, fur");
    }

    public void setColor(String type, int color) {
        switch (type.toLowerCase()) {
            case "aura":
                auraColor = color;
                break;
            case "hair":
                hairColor = color;
                break;
            case "eye":
                eyeColor = color;
                break;
            case "bodycm":
                bodyCM = color;
                break;
            case "bodyc1":
                bodyC1 = color;
                break;
            case "bodyc2":
                bodyC2 = color;
                break;
            case "bodyc3":
                bodyC3 = color;
                break;
            case "fur":
                furColor = color;
                break;
            default:
                throw new CustomNPCsException("Invalid type! Legal types: aura, hair, eye, bodycm, bodyc1, bodyc2, bodyc3, fur");
        }
    }

    @Override
    public void setHairType(String type) {
        String s = type.toLowerCase();
        if (s.equals("base") || s.equals("ssj") || s.equals("ssj2") || s.equals("ssj3") || s.equals("ssj4") || s.equals("oozaru") || s.equals("")) {
            hairType = s;

        } else {
            hairType = "";
            throw new CustomNPCsException("Invalid type!");
        }
    }

    public int getNameColor() {
        if (hairType.equals("ssj4") || hairType.equals("oozaru"))
            return furColor;
        else
            return hairColor;
    }

    @Override
    public String getHairType(String type) {
        String s = type.toLowerCase();
        if (s.equals("base") || s.equals("ssj") || s.equals("ssj2") || s.equals("ssj3") || s.equals("ssj4") || s.equals("oozaru") || s.equals(""))
            return hairType;
        else
            throw new CustomNPCsException("Invalid type!");
    }

    public int getColor(String type) {
        switch (type.toLowerCase()) {
            case "aura":
                return auraColor;
            case "hair":
                return hairColor;
            case "eye":
                return eyeColor;
            case "bodycm":
                return bodyCM;
            case "bodyc1":
                return bodyC1;
            case "bodyc2":
                return bodyC2;
            case "bodyc3":
                return bodyC3;
            case "fur":
                return furColor;
        }
        throw new CustomNPCsException("Invalid type! Legal types: aura, hair, eye, bodycm, bodyc1, bodyc2, bodyc3, fur");
    }

    public void setState2Factor(int dbcForm, float factor) {
        switch (dbcForm) {
            case DBCForm.Kaioken:
                kaiokenState2Factor = factor;
                break;
            case DBCForm.UltraInstinct:
                uiState2Factor = factor;
                break;
        }
    }

    public float getState2Factor(int dbcForm) {
        switch (dbcForm) {
            case DBCForm.Kaioken:
                return kaiokenState2Factor;
            case DBCForm.UltraInstinct:
                return uiState2Factor;
            default:
                return 1.0f;
        }
    }

    @Override
    public float getSize() {
        return formSize;
    }

    @Override
    public void setSize(float size) {
        formSize = Math.min(size, 50);
    }

    @Override
    public boolean hasSize() {
        return hasSize;
    }

    @Override
    public void setHasSize(boolean hasSize) {
        this.hasSize = hasSize;
    }

    public float[] getAllMulti() {
        return new float[]{strengthMulti, dexMulti, willMulti};
    }

    @Override
    public void setAllMulti(float allMulti) {
        this.strengthMulti = allMulti;
        this.dexMulti = allMulti;
        this.willMulti = allMulti;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getMenuName() {
        return menuName;
    }

    @Override
    public void setMenuName(String name) {
        if (name.contains("&"))
            name = name.replace("&", "§");

        this.menuName = name;
    }


    @Override
    public int getRace() {
        return race;
    }

    @Override
    public void setRace(int race) {
        this.race = race;
    }

    @Override
    public void setAttributeMulti(int id, float multi) {
        switch (id) {
            case 0:
                strengthMulti = multi;
                break;
            case 1:
                dexMulti = multi;
                break;
            case 3:
                willMulti = multi;
                break;
        }
    }

    @Override
    public float getAttributeMulti(int id) {
        switch (id) {
            case 0:
                return strengthMulti;
            case 1:
                return dexMulti;
            case 3:
                return willMulti;

        }
        return 1.0f;
    }

    public boolean isFormStackable(int dbcForm) {
        switch (dbcForm) {
            case DBCForm.Kaioken:
                return kaiokenStackable;
            case DBCForm.UltraInstinct:
                return uiStackable;
            case DBCForm.GodOfDestruction:
                return godStackable;
            case DBCForm.Mystic:
                return mysticStackable;
            default:
                return false;
        }
    }


    public void stackForm(int dbcForm, boolean stackForm) {
        switch (dbcForm) {
            case DBCForm.Kaioken:
                kaiokenStackable = stackForm;
                break;
            case DBCForm.UltraInstinct:
                uiStackable = stackForm;
                break;
            case DBCForm.GodOfDestruction:
                godStackable = stackForm;
                break;
            case DBCForm.Mystic:
                mysticStackable = stackForm;
                break;
        }
    }

    public void setFormMulti(int dbcForm, float multi) {
        switch (dbcForm) {
            case DBCForm.Kaioken:
                kaiokenStrength = multi;
                break;
            case DBCForm.UltraInstinct:
                uiStrength = multi;
                break;
            case DBCForm.GodOfDestruction:
                godStrength = multi;
                break;
            case DBCForm.Mystic:
                mysticStrength = multi;
                break;
        }
    }

    public float getFormMulti(int dbcForm) {
        switch (dbcForm) {
            case DBCForm.Kaioken:
                return kaiokenStrength;
            case DBCForm.UltraInstinct:
                return uiStrength;
            case DBCForm.GodOfDestruction:
                return godStrength;
            case DBCForm.Mystic:
                return mysticStrength;
            default:
                return 1.0f;
        }
    }

    @Override
    public int getAuraColor() {
        return auraColor;
    }

    @Override
    public void setAuraColor(int auraColor) {
        this.auraColor = auraColor;
    }

    @Override
    public void assignToPlayer(EntityPlayer p) {
        if (race == DBCRace.ALL || race == DBCData.get(p).Race) {
            PlayerData playerData = PlayerDataController.Instance.getPlayerData(p);
            PlayerCustomFormData formData = Utility.getFormData(playerData);
            formData.addForm(this);
            formData.updateClient();
            playerData.updateClient = true;
            playerData.save();
        }
    }

    public void assignToPlayer(String name) {
        assignToPlayer(NpcAPI.Instance().getPlayer(name).getMCEntity());
    }


    @Override
    public void removeFromPlayer(EntityPlayer p) {
        PlayerData playerData = PlayerDataController.Instance.getPlayerData(p);
        PlayerCustomFormData formData = Utility.getFormData(playerData);
        formData.removeForm(this);
        if (formData.selectedForm == this.id)
            formData.selectedForm = -1;

        formData.updateClient();
        playerData.save();
    }

    public void removeFromPlayer(String name) {
        removeFromPlayer(NpcAPI.Instance().getPlayer(name).getMCEntity());
    }

    @Override
    public String getAscendSound() {
        return ascendSound;
    }

    @Override
    public void setAscendSound(String directory) {
        ascendSound = directory;
    }

    @Override
    public String getDescendSound() {
        return descendSound;
    }

    @Override
    public void setDescendSound(String directory) {
        descendSound = directory;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int newID) {
        id = newID;
    }

    @Override
    public void linkChild(int formID) {
        if(formID == this.id)
            return;

        CustomForm form = (CustomForm) FormController.getInstance().get(formID);
        if(form != null){
            childID = formID;
            form.parentID = this.id;
        }
    }

    @Override
    public void linkChild(ICustomForm form) { linkChild(form.getID()); }

    @Override
    public void linkParent(int formID) {
        if(formID == this.id)
            return;

        CustomForm form = (CustomForm) FormController.getInstance().get(formID);
        if(form != null){
            parentID = formID;
            form.childID = this.id;
        }
    }

    @Override
    public void linkParent(ICustomForm form) {
        linkParent(form.getID());
    }


    public ICustomForm getChild() {
        return FormController.Instance.get(childID);
    }

    //internal
    public CustomForm getC() {
        return (CustomForm) FormController.Instance.get(childID);
    }

    @Override
    public int getChildID() {
        return childID;
    }

    @Override
    public boolean hasChild() {
        return childID != -1;
    }


    public void removeChildForm(){
        childID = -1;
    }

    public ICustomForm getParent() {
        return FormController.Instance.get(parentID);
    }

    //internal
    public CustomForm getP() {
        return (CustomForm) FormController.Instance.get(parentID);
    }

    @Override
    public int getParentID() {
        return parentID;
    }

    @Override
    public boolean hasParent() {
        return parentID != -1;
    }

    public void removeParentForm() {
        parentID = -1;
    }

    @Override
    public IFormMastery getFormMastery() {
        return formMastery;
    }

    //internal usage
    public FormMastery getFM() {
        return formMastery;
    }

    public void readFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("ID"))
            id = compound.getInteger("ID");
        else if (AnimationController.Instance != null)
            id = FormController.Instance.getUnusedId();

        name = compound.getString("name");
        menuName = compound.getString("menuName");
        race = compound.getInteger("race");
        formSize = compound.getFloat("formSize");
        hasSize = compound.getBoolean("hasSize");
        childID = compound.getInteger("childID");
        parentID = compound.getInteger("parentID");

        NBTTagCompound attributes = compound.getCompoundTag("attributes");
        strengthMulti = attributes.getFloat("strMulti");
        dexMulti = attributes.getFloat("dexMulti");
        willMulti = attributes.getFloat("willMulti");

        NBTTagCompound stack = compound.getCompoundTag("stackableForms");
        kaiokenStrength = stack.getFloat("kaiokenStrength");
        kaiokenStackable = stack.getBoolean("kaiokenStackable");
        kaiokenState2Factor = stack.getFloat("kaiokenState2Factor");
        uiStrength = stack.getFloat("uiStrength");
        uiStackable = stack.getBoolean("uiStackable");
        uiState2Factor = stack.getFloat("uiState2Factor");
        godStrength = stack.getFloat("godStrength");
        godStackable = stack.getBoolean("godStackable");
        mysticStrength = stack.getFloat("mysticStrength");
        mysticStackable = stack.getBoolean("mysticStackable");


        NBTTagCompound rendering = compound.getCompoundTag("rendering");
        auraColor = rendering.getInteger("auraColor");
        eyeColor = rendering.getInteger("eyeColor");
        hairColor = rendering.getInteger("hairColor");
        furColor = rendering.getInteger("furColor");
        hairCode = rendering.getString("hairCode");
        hairType = rendering.getString("hairType");
        bodyCM = rendering.getInteger("bodyCM");
        bodyC1 = rendering.getInteger("bodyC1");
        bodyC2 = rendering.getInteger("bodyC2");
        bodyC3 = rendering.getInteger("bodyC3");

        NBTTagCompound sounds = compound.getCompoundTag("sounds");
        ascendSound = sounds.getString("ascendSound");
        descendSound = sounds.getString("descendSound");

        formMastery.readFromNBT(compound);
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("ID", id);
        compound.setString("name", name);
        compound.setString("menuName", menuName);
        compound.setInteger("race", race);
        compound.setFloat("formSize", formSize);
        compound.setBoolean("hasSize", hasSize);
        compound.setInteger("childID", childID);
        compound.setInteger("parentID", parentID);

        NBTTagCompound attributes = new NBTTagCompound();
        compound.setTag("attributes", attributes);
        attributes.setFloat("strMulti", strengthMulti);
        attributes.setFloat("dexMulti", dexMulti);
        attributes.setFloat("willMulti", willMulti);

        NBTTagCompound stack = new NBTTagCompound();
        compound.setTag("stackableForms", stack);
        stack.setFloat("kaiokenStrength", kaiokenStrength);
        stack.setBoolean("kaiokenStackable", kaiokenStackable);
        stack.setFloat("kaiokenState2Factor", kaiokenState2Factor);
        stack.setFloat("uiStrength", uiStrength);
        stack.setBoolean("uiStackable", uiStackable);
        stack.setFloat("uiState2Factor", uiState2Factor);
        stack.setFloat("godStrength", godStrength);
        stack.setBoolean("godStackable", godStackable);
        stack.setFloat("mysticStrength", mysticStrength);
        stack.setBoolean("mysticStackable", mysticStackable);

        NBTTagCompound rendering = new NBTTagCompound();
        compound.setTag("rendering", rendering);
        rendering.setInteger("auraColor", auraColor);
        rendering.setInteger("eyeColor", eyeColor);
        rendering.setInteger("hairColor", hairColor);
        rendering.setString("hairCode", hairCode);
        rendering.setString("hairType", hairType);
        rendering.setInteger("furColor", furColor);
        rendering.setInteger("bodyCM", bodyCM);
        rendering.setInteger("bodyC1", bodyC1);
        rendering.setInteger("bodyC2", bodyC2);
        rendering.setInteger("bodyC3", bodyC3);

        NBTTagCompound sounds = new NBTTagCompound();
        sounds.setString("ascendSound", ascendSound);
        sounds.setString("descendSound", descendSound);
        compound.setTag("sounds", sounds);

        formMastery.writeToNBT(compound);
        return compound;
    }

    @Override
    public ICustomForm save() {
        return FormController.Instance.saveForm(this);
    }
}

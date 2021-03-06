package com.toyknight.aeii.gui;

import com.toyknight.aeii.core.map.TileRepository;
import com.toyknight.aeii.core.unit.UnitFactory;
import com.toyknight.aeii.gui.util.ResourceUtil;
import com.toyknight.aeii.core.SuffixFileFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author toyknight
 */
public class ResourceManager {

    private static final SuffixFileFilter png_file_filter = new SuffixFileFilter("png");

    private static BufferedImage img_title;
    private static BufferedImage img_background;
    private static BufferedImage img_title_mask;
    private static BufferedImage img_title_glow;
    private static BufferedImage gold_icon;
    private static BufferedImage attack_icon;
    private static BufferedImage red_defence_icon;
    private static BufferedImage blue_defence_icon;
    private static BufferedImage level_icon;
    private static BufferedImage population_icon;
    private static BufferedImage poisoned_status;
    private static BufferedImage[] action_icons;
    private static BufferedImage[] small_circles;
    private static BufferedImage[] big_circles;
    private static BufferedImage[] borders;
    private static BufferedImage[] tiles;
    private static BufferedImage[] stiles;
    private static BufferedImage[] top_tiles;
    private static BufferedImage tomb;
    private static BufferedImage[][][] units;
    private static BufferedImage[] small_units;
    private static BufferedImage[] mini_unit_icons;
    private static BufferedImage[][] standby_units;
    private static BufferedImage[] cursor;
    private static BufferedImage move_target;
    private static BufferedImage[] attack_cursor;
    private static BufferedImage[] attack_spark;
    private static BufferedImage move_alpha;
    private static BufferedImage attack_alpha;
    private static BufferedImage[] white_spark;
    private static BufferedImage[] smoke;
    private static BufferedImage[] numbers;
    private static BufferedImage[] lnumbers;
    private static BufferedImage minus;
    private static BufferedImage lplus;
    private static BufferedImage lminus;
    private static BufferedImage ldivision;
    private static BufferedImage rise_arrow;
    private static BufferedImage reduce_arrow;
    private static BufferedImage level_up_icon;
    private static Color aeii_panel_bg;
    private static Color text_background;
    private static Color move_path_color;
    private static Color p_attack_color;
    private static Color m_attack_color;
    private static Color[] team_color;
    private static Font title_font;
    private static Font label_font;
    private static Font text_font;

    private ResourceManager() {
    }

    public static void init(int ts) throws IOException {
        /*img_title = ImageIO.read(new File("res/img/logo.png"));
        img_title_mask = new BufferedImage(img_title.getWidth(), img_title.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //background cho phan logo
        for (int x = 0; x < img_title.getWidth(); x++) {
            for (int y = 0; y < img_title.getHeight(); y++) {
                int rgb = img_title.getRGB(x, y);
                if ((rgb >> 24) == 0) {
                    img_title_mask.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }*/
        img_background = ImageIO.read(new File("res/img/grassbg1.gif"));
        /*img_title_glow = ImageIO.read(new File("res/img/glow.png"));*/
        loadBorder();
        loadTiles(ts);
        loadTopTiles(ts);
        loadCursors(ts);
        loadUnits(ts);
        loadSparks(ts);
        loadAlpha(ts);
        loadSmoke(ts);
        loadChars(ts);
        loadCircles(ts);
        loadActionIcons(ts);
        loadIcons(ts);
        loadStatus(ts);
        loadArrows(ts);
        aeii_panel_bg = new Color(36, 42, 69);
        text_background = new Color(206, 206, 206);
        move_path_color = new Color(225, 0, 82);
        p_attack_color = new Color(227, 0, 117);
        m_attack_color = new Color(0, 0, 255);
        team_color = new Color[4];
        team_color[0] = new Color(0, 100, 198);
        team_color[1] = new Color(161, 0, 112);
        team_color[2] = new Color(0, 153, 55);
        team_color[3] = new Color(0, 65, 114);
        title_font = new Font(Font.DIALOG, Font.BOLD, ts / 2);
        label_font = new Font(Font.DIALOG, Font.BOLD, ts / 3);
        text_font = new Font(Font.DIALOG, Font.PLAIN, ts / 4);
    }

    private static void loadBorder() throws IOException {
        BufferedImage img_borders = ImageIO.read(new File("res/img/border.png"));
        borders = new BufferedImage[8];
        for (int i = 0; i < borders.length; i++) {
            BufferedImage border
                    = ResourceUtil.getImageClip(img_borders, 16 * i, 0, 16, 16);
            borders[i] = border;
        }
    }

    private static void loadTiles(int ts) throws IOException {
        int tile_count = TileRepository.getTileCount();
        tiles = new BufferedImage[tile_count];
        for (int i = 0; i < tile_count; i++) {
            File tile = new File("res/img/tiles/tile_" + i + ".png");

            tiles[i] = new BufferedImage(ts, ts, BufferedImage.TYPE_INT_ARGB);
            tiles[i].getGraphics().drawImage(ImageIO.read(tile), 0, 0, ts, ts, null);
        }
        File stile_dir = new File("res/img/stiles");
        int stile_count = stile_dir.listFiles(png_file_filter).length;
        stiles = new BufferedImage[stile_count];
        for (int i = 0; i < stile_count; i++) {
            File stile = new File("res/img/stiles/stiles" + i + ".png");
            stiles[i] = ImageIO.read(stile);
        }
        File tomb_file = new File("res/img/tombstone.png");
        tomb = new BufferedImage(ts, ts, BufferedImage.TYPE_INT_ARGB);
        tomb.getGraphics().drawImage(ImageIO.read(tomb_file), 0, 0, ts, ts, null);
    }

    private static void loadTopTiles(int ts) throws IOException {
        File img_top_tile_dir = new File("res/img/tiles/top_tiles");
        int top_tile_count = img_top_tile_dir.listFiles(png_file_filter).length;
        top_tiles = new BufferedImage[top_tile_count];
        for (int i = 0; i < top_tile_count; i++) {
            File tile = new File("res/img/tiles/top_tiles/top_tile_" + i + ".png");
            top_tiles[i] = new BufferedImage(ts, ts, BufferedImage.TYPE_INT_ARGB);
            top_tiles[i].getGraphics().drawImage(ImageIO.read(tile), 0, 0, ts, ts, null);
        }
    }

    private static void loadUnits(int ts) throws IOException {
        int unit_count = UnitFactory.getUnitCount();
        units = new BufferedImage[4][unit_count][2];
        standby_units = new BufferedImage[4][unit_count];
        for (int team = 0; team < 4; team++) {
            File unit_team = new File("res/img/units/unit_icons_" + team + ".png");
            BufferedImage img_units
                    = new BufferedImage(unit_count * ts, ts * 2, BufferedImage.TYPE_INT_ARGB);
            img_units.getGraphics().drawImage(
                    ImageIO.read(unit_team),
                    0, 0,
                    unit_count * ts, ts * 2,
                    null);
            for (int index = 0; index < unit_count; index++) {
                BufferedImage unit_f0 = new BufferedImage(ts, ts, BufferedImage.TYPE_INT_ARGB);
                BufferedImage unit_f1 = new BufferedImage(ts, ts, BufferedImage.TYPE_INT_ARGB);
                unit_f0.getGraphics().drawImage(
                        img_units,
                        0 - index * ts, 0,
                        img_units.getWidth(), img_units.getHeight(),
                        null);
                unit_f1.getGraphics().drawImage(
                        img_units,
                        0 - index * ts, -ts,
                        img_units.getWidth(), img_units.getHeight(),
                        null);
                units[team][index][0] = unit_f0;
                units[team][index][1] = unit_f1;
                standby_units[team][index] = ResourceUtil.getGrayScaledImage(unit_f0);
            }
        }

        int sus = ts / 24 * 10;
        small_units = new BufferedImage[unit_count];
        File small_unit_file = new File("res/img/unit_icons_small.png");
        BufferedImage img_small_unit = new BufferedImage(sus * unit_count, sus, BufferedImage.TYPE_INT_ARGB);
        img_small_unit.getGraphics().drawImage(ImageIO.read(small_unit_file), 0, 0, sus * unit_count, sus, null);
        for (int index = 0; index < unit_count; index++) {
            small_units[index] = ResourceUtil.getImageClip(img_small_unit, sus * index, 0, sus, sus);
        }

        mini_unit_icons = new BufferedImage[4];
        File mini_unit_icon_file = new File("res/img/mini_unit_icons.png");
        BufferedImage mini_unit_icon_list = ImageIO.read(mini_unit_icon_file);
        for (int team = 0; team < 4; team++) {
            mini_unit_icons[team] = ResourceUtil.getImageClip(mini_unit_icon_list, team * 10, 0, 10, 10);
        }
    }
    // load vị trí hiện tải 
    private static void loadCursors(int ts) throws IOException {
        ts = ts / 24 * 26;
        cursor = new BufferedImage[0];
        File cursor_file = new File("res/img/cursor_" + 0 + ".png");
        cursor[0] = new BufferedImage(ts, ts, BufferedImage.TYPE_INT_ARGB);
        cursor[0].getGraphics().drawImage(ImageIO.read(cursor_file), 0, 0, ts, ts, null);
        File move_target_file = new File("res/img/move_target_cursor.png");
        move_target = new BufferedImage(ts, ts, BufferedImage.TYPE_INT_ARGB);
        move_target.getGraphics().drawImage(ImageIO.read(move_target_file), 0, 0, ts, ts, null);
        int acw = ts / 26 * 40;
        int ach = ts / 26 * 41;
        attack_cursor = new BufferedImage[0];
        File cursor_file_attack = new File("res/img/attack_cursor_" + 0 + ".png");
        attack_cursor[0] = new BufferedImage(acw, ach, BufferedImage.TYPE_INT_ARGB);
        attack_cursor[0].getGraphics().drawImage(ImageIO.read(cursor_file_attack), 0, 0, acw, ach, null);
    }

    private static void loadSparks(int ts) throws IOException {
        int asts = ts / 24 * 20;
        attack_spark = new BufferedImage[6];
        File attack_spark_file = new File("res/img/attack_spark.png");
        BufferedImage attack_sparks = new BufferedImage(asts * 6, asts, BufferedImage.TYPE_INT_ARGB);
        attack_sparks.getGraphics().drawImage(ImageIO.read(attack_spark_file), 0, 0, asts * 6, asts, null);
        for (int i = 0; i < 6; i++) {
            attack_spark[i] = ResourceUtil.getImageClip(attack_sparks, asts * i, 0, asts, asts);
        }
        white_spark = new BufferedImage[6];
        File spark_file = new File("res/img/white_spark.png");
        BufferedImage white_sparks = new BufferedImage(ts * 6, ts, BufferedImage.TYPE_INT_ARGB);
        white_sparks.getGraphics().drawImage(ImageIO.read(spark_file), 0, 0, ts * 6, ts, null);
        for (int i = 0; i < 6; i++) {
            white_spark[i] = ResourceUtil.getImageClip(white_sparks, ts * i, 0, ts, ts);
        }
    }

    private static void loadAlpha(int ts) throws IOException {
        File alpha_file = new File("res/img/alpha.png");
        BufferedImage img_alpha = new BufferedImage(ts * 2, ts, BufferedImage.TYPE_INT_ARGB);
        img_alpha.getGraphics().drawImage(ImageIO.read(alpha_file), 0, 0, ts * 2, ts, null);
        move_alpha = ResourceUtil.getImageClip(img_alpha, ts, 0, ts, ts);
        attack_alpha = ResourceUtil.getImageClip(img_alpha, 0, 0, ts, ts);
    }

    private static void loadSmoke(int ts) throws IOException {
        int h = ts / 24 * 20;
        smoke = new BufferedImage[4];
        File smoke_file = new File("res/img/smoke.png");
        BufferedImage smokes = new BufferedImage(ts * 4, h, BufferedImage.TYPE_INT_ARGB);
        smokes.getGraphics().drawImage(ImageIO.read(smoke_file), 0, 0, ts * 4, h, null);
        for (int i = 0; i < 4; i++) {
            smoke[i] = ResourceUtil.getImageClip(smokes, ts * i, 0, ts, h);
        }
    }

    private static void loadChars(int ts) throws IOException {
        int w = ts / 24 * 6;
        int h = ts / 24 * 7;
        int lw = ts / 24 * 8;
        int lh = ts / 24 * 11;
        File chars_file = new File("res/img/chars.png");
        BufferedImage img_chars = new BufferedImage(w * 12, h, BufferedImage.TYPE_INT_ARGB);
        img_chars.getGraphics().drawImage(ImageIO.read(chars_file), 0, 0, w * 12, h, null);
        File lchars_file = new File("res/img/lchars.png");
        BufferedImage img_lchars = new BufferedImage(lw * 13, lh, BufferedImage.TYPE_INT_ARGB);
        img_lchars.getGraphics().drawImage(ImageIO.read(lchars_file), 0, 0, lw * 13, lh, null);
        numbers = new BufferedImage[10];
        lnumbers = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = ResourceUtil.getImageClip(img_chars, i * w, 0, w, h);
            lnumbers[i] = ResourceUtil.getImageClip(img_lchars, i * lw, 0, lw, lh);
        }
        minus = ResourceUtil.getImageClip(img_chars, 10 * w, 0, w, h);
        lminus = ResourceUtil.getImageClip(img_lchars, 11 * lw, 0, lw, lh);
        lplus = ResourceUtil.getImageClip(img_lchars, 12 * lw, 0, lw, lh);
        ldivision = ResourceUtil.getImageClip(img_lchars, 10 * lw, 0, lw, lh);
    }

    private static void loadCircles(int ts) throws IOException {
        int scw = ts / 24 * 20;
        int sch = ts / 24 * 21;
        int bcw = ts / 24 * 32;
        int bch = ts / 24 * 33;
        big_circles = new BufferedImage[2];
        small_circles = new BufferedImage[2];
        File small_circle_file = new File("res/img/small_circle.png");
        BufferedImage img_small_circle = new BufferedImage(scw * 2, sch, BufferedImage.TYPE_INT_ARGB);
        img_small_circle.getGraphics().drawImage(ImageIO.read(small_circle_file), 0, 0, scw * 2, sch, null);
        for (int i = 0; i < 2; i++) {
            small_circles[i] = ResourceUtil.getImageClip(img_small_circle, scw * i, 0, scw, sch);
            File big_circle_file = new File("res/img/big_circle_" + i + ".png");
            big_circles[i] = new BufferedImage(bcw * 2, bch, BufferedImage.TYPE_INT_ARGB);
            big_circles[i].getGraphics().drawImage(ImageIO.read(big_circle_file), 0, 0, bcw, bch, null);
        }
    }

    private static void loadActionIcons(int ts) throws IOException {
        int iw = ts / 24 * 16;
        int ih = ts / 24 * 16;
        File icons_file = new File("res/img/action_icons.png");
        BufferedImage action_icon_image = new BufferedImage(iw * 8, ih, BufferedImage.TYPE_INT_ARGB);
        action_icon_image.getGraphics().drawImage(ImageIO.read(icons_file), 0, 0, iw * 8, ih, null);
        action_icons = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            action_icons[i] = ResourceUtil.getImageClip(action_icon_image, iw * i, 0, iw, ih);
        }
    }

    private static void loadIcons(int ts) throws IOException {
        int hw = ts / 24 * 13;
        int hh = ts / 24 * 16;
        File hud_icon_file = new File("res/img/hud_icons.png");
        BufferedImage img_hud_icon = new BufferedImage(hw * 4, hh, BufferedImage.TYPE_INT_ARGB);
        img_hud_icon.getGraphics().drawImage(ImageIO.read(hud_icon_file), 0, 0, hw * 4, hh, null);
        attack_icon = ResourceUtil.getImageClip(img_hud_icon, 0, 0, hw, hh);
        red_defence_icon = ResourceUtil.getImageClip(img_hud_icon, hw, 0, hw, hh);
        blue_defence_icon = ResourceUtil.getImageClip(img_hud_icon, hw * 2, 0, hw, hh);
        level_icon = ResourceUtil.getImageClip(img_hud_icon, hw * 3, 0, hw, hh);
        int i2w = ts / 24 * 11;
        int i2h = ts / 24 * 11;
        File hud_icon2_file = new File("res/img/hud_icons_2.png");
        BufferedImage img_hud_icon2 = new BufferedImage(i2w * 2, i2h, BufferedImage.TYPE_INT_ARGB);
        img_hud_icon2.getGraphics().drawImage(ImageIO.read(hud_icon2_file), 0, 0, i2w * 2, i2h, null);
        gold_icon = ResourceUtil.getImageClip(img_hud_icon2, i2w, 0, i2w, i2h);
        population_icon = ResourceUtil.getImageClip(img_hud_icon2, 0, 0, i2w, i2h);
        int luiw = ts / 24 * 62;
        int luih = ts / 24 * 11;
        File level_up_icon_file = new File("res/img/levelup.png");
        level_up_icon = new BufferedImage(luiw, luih, BufferedImage.TYPE_INT_ARGB);
        level_up_icon.getGraphics().drawImage(ImageIO.read(level_up_icon_file), 0, 0, luiw, luih, null);
    }

    private static void loadStatus(int ts) throws IOException {
        int sw = ts / 24 * 7;
        int sh = ts / 24 * 9;
        File status_file = new File("res/img/status.png");
        BufferedImage status_list = new BufferedImage(sw * 2, sh, BufferedImage.TYPE_INT_ARGB);
        status_list.getGraphics().drawImage(ImageIO.read(status_file), 0, 0, sw * 2, sh, null);
        poisoned_status = ResourceUtil.getImageClip(status_list, 0, 0, sw, sh);
    }

    private static void loadArrows(int ts) throws IOException {
        int aw = ts / 24 * 9;
        int ah = ts / 24 * 7;
        File arrow_file = new File("res/img/arrow_icons.png");
        BufferedImage img_arrows = new BufferedImage(aw * 3, ah, BufferedImage.TYPE_INT_ARGB);
        img_arrows.getGraphics().drawImage(ImageIO.read(arrow_file), 0, 0, aw * 3, ah, null);
        rise_arrow = ResourceUtil.getImageClip(img_arrows, aw, 0, aw, ah);
        reduce_arrow = ResourceUtil.getImageClip(img_arrows, aw * 2, 0, aw, ah);
    }
    
    public static BufferedImage getBackGroudImage() {
    	return img_background;
    }
    
    public static BufferedImage getTitleImage() {
        return img_title;
    }

    public static BufferedImage getTitleMask() {
        return img_title_mask;
    }

    public static BufferedImage getTitleGlow() {
        return img_title_glow;
    }

    public static BufferedImage getBorderImage(int index) {
        return borders[index];
    }

    public static BufferedImage getAttackIcon() {
        return attack_icon;
    }

    public static BufferedImage getRedDefenceIcon() {
        return red_defence_icon;
    }

    public static BufferedImage getBlueDefenceIcon() {
        return blue_defence_icon;
    }

    public static BufferedImage getLevelIcon() {
        return level_icon;
    }

    public static BufferedImage getLevelUpIcon() {
        return level_up_icon;
    }

    public static BufferedImage getPopulationIcon() {
        return population_icon;
    }

    public static BufferedImage getGoldIcon() {
        return gold_icon;
    }

    public static BufferedImage getTileImage(int index) {
        return tiles[index];
    }

    public static BufferedImage getSTileImage(int index) {
        return stiles[index];
    }

    public static BufferedImage getTopTileImage(int index) {
        return top_tiles[index];
    }

    public static BufferedImage getTombImage() {
        return tomb;
    }

    public static BufferedImage getUnitImage(int team, int index, int frame) {
        return units[team][index][frame];
    }

    public static BufferedImage getStandbyUnitImage(int team, int index) {
        return standby_units[team][index];
    }

    public static BufferedImage getSmallUnitImage(int index) {
        return small_units[index];
    }
    
    public static BufferedImage getMiniUnitIcon(int team) {
        return mini_unit_icons[team];
    }

    public static BufferedImage getCursorImage(int index) {
        return cursor[index];
    }

    public static BufferedImage getMoveTargetCursorImage() {
        return move_target;
    }

    public static BufferedImage getAttackCursorImage(int index) {
        return attack_cursor[index];
    }

    public static BufferedImage getAttackSparkImage(int index) {
        return attack_spark[index];
    }

    public static BufferedImage getWhiteSparkImage(int index) {
        return white_spark[index];
    }

    public static BufferedImage getSmokeImage(int index) {
        return smoke[index];
    }

    public static BufferedImage getAttackAlpha() {
        return attack_alpha;
    }

    public static BufferedImage getMoveAlpha() {
        return move_alpha;
    }

    public static BufferedImage getNumber(int n) {
        return numbers[n];
    }

    public static BufferedImage getLNumber(int n) {
        return lnumbers[n];
    }

    public static BufferedImage getMinus() {
        return minus;
    }

    public static BufferedImage getLPlus() {
        return lplus;
    }

    public static BufferedImage getLMinus() {
        return lminus;
    }

    public static BufferedImage getLDivision() {
        return ldivision;
    }

    public static BufferedImage getPoisonedStatusImage() {
        return poisoned_status;
    }

    public static BufferedImage getActionIcon(int index) {
        return action_icons[index];
    }

    public static BufferedImage getSmallCircleImage(int index) {
        return small_circles[index];
    }

    public static BufferedImage getBigCircleImage(int index) {
        return big_circles[index];
    }

    public static BufferedImage getRiseArrow() {
        return rise_arrow;
    }

    public static BufferedImage getReduceArrow() {
        return reduce_arrow;
    }

    public static Color getPhysicalAttackColor() {
        return p_attack_color;
    }

    public static Color getMagicalAttackColor() {
        return m_attack_color;
    }

    public static Color getTeamColor(int team) {
        return team_color[team];
    }

    public static Color getAEIIPanelBg() {
        return aeii_panel_bg;
    }

    public static Color getTextBackgroundColor() {
        return text_background;
    }

    public static Color getMovePathColor() {
        return move_path_color;
    }

    public static Font getTitleFont() {
        return title_font;
    }

    public static Font getLabelFont() {
        return label_font;
    }

    public static Font getTextFont() {
        return text_font;
    }

}

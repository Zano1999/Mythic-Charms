package net.masik.mythiccharms.item;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TooltipItem extends Item {

    private final String tooltipKey;
    private final Boolean notNormal;

    public TooltipItem(Settings settings, String tooltip, Boolean shift) {
        super(settings);
        tooltipKey = tooltip;
        notNormal = shift;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        List<String> tooltipLines = new ArrayList<>();

        int width = 40;

        int cursor = 0;

        String wideTooltip = Text.translatable(tooltipKey).getString();

        int spaceSymbol = 0;

        while (spaceSymbol != -1) {
            spaceSymbol = wideTooltip.indexOf(' ', cursor + width);
            tooltipLines.add(wideTooltip.substring(cursor, spaceSymbol != -1 ? spaceSymbol : wideTooltip.length()));
            cursor = spaceSymbol + 1;
        }

        if (notNormal) {

            if (Screen.hasShiftDown()) {

                for (String tooltipLine : tooltipLines) {
                    tooltip.add(Text.of("§5§o%s".formatted(tooltipLine)));
                }

            } else {

                tooltip.add(Text.translatable("util.mythic_charms.shift_tooltip"));

            }

        } else {

            for (String tooltipLine : tooltipLines) {
                tooltip.add(Text.of("§7§o%s".formatted(tooltipLine)));
            }

        }

    }

}
package me.mooy1.infinityexpansion.setup.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class InfinityTabCompleter implements TabCompleter {

    private static final int MAX_SUGGESTIONS = 80;

    private final InfinityCommand command;

    public InfinityTabCompleter(@Nonnull InfinityCommand command) {
        this.command = command;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, String[] args) {
        if (args.length == 1) {
            return createReturnList(command.getSubCommandNames(), args[0]);
        }

        return null;
    }

    @Nonnull
    private List<String> createReturnList(@Nonnull List<String> list, @Nonnull String string) {
        if (string.length() == 0) {
            return list;
        }

        String input = string.toLowerCase(Locale.ROOT);
        List<String> returnList = new LinkedList<>();

        for (String item : list) {
            if (item.toLowerCase(Locale.ROOT).contains(input)) {
                returnList.add(item);

                if (returnList.size() >= MAX_SUGGESTIONS) {
                    break;
                }
            } else if (item.equalsIgnoreCase(input)) {
                return Collections.emptyList();
            }
        }

        return returnList;
    }
}
package com.enya.test.nio;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yhrush on 4/28/2016.
 */
public class DirFilter {


    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:\\Content\\Targets\\USA\\Templates");

        Stopwatch s = Stopwatch.createStarted();
        Files.newDirectoryStream(path);
        System.out.println("1. time: " + s.elapsed(TimeUnit.MILLISECONDS));

        List<Path> paths = Lists.newArrayList(Files.newDirectoryStream(path, new Filter()));

        System.out.println(paths.size() + " time: " + s.elapsed(TimeUnit.MILLISECONDS));
        for (Path path1 : paths)
            System.out.println(path1);
    }


    static class Filter implements DirectoryStream.Filter<Path> {

        final List<String> names = new ArrayList<>();

        {
            names.add("suite1a.en.U-0131-01.CDDVDLabel.ProposalMeeting1.00.xml");
            names.add("en.U-0574-01.DIFYOneSidedNoteCardWide.DCCameraLens.1505-01.xml");
            names.add("en.U-0573-01.DIFYOneSidedInvitationTall.JMPoms.1408-01.xml");
            names.add("en.U-0358-01.PrintableTagsWide.DCWizardFeathersGreen.1503-01.xml");
            names.add("en.U-0121-01.8UpAdhesiveNameBadge.DCHello.1501-01.xml");
        }


        public boolean accept(Path entry) throws IOException {
            return !Files.isDirectory(entry) && this.names.contains(entry.getFileName().toString());
        }
    }
}
